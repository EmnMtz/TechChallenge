package com.techchallenge.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techchallenge.model.Inventory;
import com.techchallenge.model.Order;
import com.techchallenge.model.OrderDTO;
import com.techchallenge.model.Shipping;
import com.techchallenge.repository.InventoryRepo;
import com.techchallenge.repository.OrderRepo;
import com.techchallenge.repository.ShippingRepo;

import jakarta.validation.Valid;

@Controller
public class OrderController {

	@Autowired
	private InventoryRepo invRepo;

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private ShippingRepo shipRepo;

	@GetMapping("/createorder")
	public String createOrder(Model model) {
		OrderDTO orderDTO = new OrderDTO();
		model.addAttribute(orderDTO);

		List<Inventory> inventory = invRepo.findAll();
		model.addAttribute("inventory", inventory);
		model.addAttribute("success", false);
		return "createorder";
	}

	@PostMapping("/createorder")
	public String register(Model model, @Valid @ModelAttribute OrderDTO orderDTO, BindingResult result) {

		List<Inventory> inventory = invRepo.findAll();
		model.addAttribute("inventory", inventory);

		if (orderDTO.getOrderItemQuantity() == null || orderDTO.getOrderItemQuantity() <= 0) {
			result.addError(new FieldError("orderDTO", "orderItemQuantity",
					"Item Quantity cannot be less than or equal to 0."));
		}

		if (result.hasErrors()) {
			return "createorder";
		}

		try {

			String pattern = "yyyyMMddHHmmssSSS";
			DateFormat df = new SimpleDateFormat(pattern);
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			cal.setTime(today);
			cal.add(Calendar.DATE, 5);
			String currentDate = df.format(today);
			String trackingOrder = currentDate + inventory.get(orderDTO.getOrderItemNumber() - 1).getItemDescription();
			pattern = "yyyy-MM-dd";
			df = new SimpleDateFormat(pattern);
			today = cal.getTime();
			currentDate = df.format(today);
			Date shippingDate = df.parse(currentDate);

			Order newOrder = new Order();
			newOrder.setOrderTrackingNumber(trackingOrder);
			newOrder.setOrderItemNumber(orderDTO.getOrderItemNumber().toString());
			newOrder.setOrderItemQuantity(orderDTO.getOrderItemQuantity());
			newOrder.setOrderComment("");
			newOrder.setOrderDate(new Date());
			newOrder.setOrderUpdateDate(new Date());
			newOrder.setOrderStatus("Pending");
			orderRepo.save(newOrder);

			Shipping newShipping = new Shipping();

			newShipping.setShippingTrackingNumber(trackingOrder);
			newShipping.setShippingComment("");
			newShipping.setShippingDate(shippingDate);
			newShipping.setShippingStatus("Pending");
			newShipping.setShippingUpdateDate(today);

			shipRepo.save(newShipping);

			model.addAttribute("orderDTO", new OrderDTO());
			model.addAttribute("success", true);

		} catch (Exception ex) {
			result.addError(new FieldError("orderDTO", "orderItemNumber", ex.getMessage()));
		}

		return "createOrder";
	}

	@GetMapping("/updateorder")
	public String showUpdateOrderPage(Model model, @RequestParam String trackingNumber) {

		List<Inventory> inventory = invRepo.findAll();

		Order order = orderRepo.findByOrderTrackingNumber(trackingNumber);
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(order, orderDTO);

		Inventory item = inventory.get(Integer.valueOf(order.getOrderItemNumber()) - 1);

		orderDTO.setOrderItemDescription(item.getItemDescription());

		model.addAttribute("orderDTO", orderDTO);

		return "updateorder";
	}

	@PostMapping("/updateorder")
	public String showUpdateOrderPage(Model model, @Valid @ModelAttribute OrderDTO orderDTO, BindingResult result) {

		if (orderDTO.getOrderItemQuantity() == null || orderDTO.getOrderItemQuantity() <= 0) {
			result.addError(new FieldError("orderDTO", "orderItemQuantity",
					"Item Quantity cannot be less than or equal to 0."));
		}

		if (result.hasErrors()) {
			return "createorder";
		}

		try {

			Date updateDate = new Date();

			Order order = orderRepo.findByOrderTrackingNumber(orderDTO.getOrderTrackingNumber());

			List<Inventory> inventory = invRepo.findAll();
			Inventory item = inventory.get(Integer.valueOf(order.getOrderItemNumber()) - 1);
			orderDTO.setOrderItemDescription(item.getItemDescription());

			Order newOrder = new Order();
			newOrder.setOrderNumber(order.getOrderNumber());
			newOrder.setOrderItemQuantity(orderDTO.getOrderItemQuantity());
			newOrder.setOrderComment(orderDTO.getOrderComment());
			newOrder.setOrderUpdateDate(updateDate);
			newOrder.setOrderTrackingNumber(order.getOrderTrackingNumber());
			newOrder.setOrderItemNumber(order.getOrderItemNumber().toString());
			newOrder.setOrderDate(order.getOrderDate());
			newOrder.setOrderStatus(order.getOrderStatus());

			orderRepo.save(newOrder);

			Shipping ship = shipRepo.findByShippingTrackingNumber(orderDTO.getOrderTrackingNumber());

			Shipping updateShip = new Shipping();
			updateShip.setShippingNumber(ship.getShippingNumber());
			updateShip.setShippingTrackingNumber(ship.getShippingTrackingNumber());
			updateShip.setShippingDate(ship.getShippingDate());
			updateShip.setShippingUpdateDate(updateDate);
			updateShip.setShippingComment(orderDTO.getOrderComment());
			updateShip.setShippingStatus(ship.getShippingStatus());

			shipRepo.save(updateShip);

			model.addAttribute("orderDTO", orderDTO);
			model.addAttribute("success", true);

		} catch (Exception ex) {
			result.addError(new FieldError("orderDTO", "orderItemNumber", ex.getMessage()));
		}

		return "updateorder";
	}

}
