package com.techchallenge.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import com.techchallenge.model.Order;
import com.techchallenge.model.Shipping;
import com.techchallenge.model.ShippingDTO;
import com.techchallenge.model.ShippingDisplay;
import com.techchallenge.repository.OrderRepo;
import com.techchallenge.repository.ShippingRepo;

import jakarta.validation.Valid;

@Controller
public class ShippingController {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private ShippingRepo shipRepo;

	@GetMapping("/shipping")
	public String showShippingPage(Model model) {

		List<ShippingDisplay> shipping = new ArrayList<ShippingDisplay>();

		List<Shipping> shippingList = shipRepo.findAll();

		try {
			for (Shipping ship : shippingList) {

				ShippingDisplay shippingDisplay = new ShippingDisplay();

				BeanUtils.copyProperties(ship, shippingDisplay);

				String pattern = "yyyy-MM-dd";
				DateFormat df = new SimpleDateFormat(pattern, Locale.ENGLISH);
				Date date = ship.getShippingDate();
				String currentDate = df.format(date);

				shippingDisplay.setShippingDate(currentDate);

				date = ship.getShippingUpdateDate();
				currentDate = df.format(date);

				shippingDisplay.setShippingUpdateDate(currentDate);

				shipping.add(shippingDisplay);
			}
		} catch (Exception ex) {

			ex.printStackTrace();
		}

		model.addAttribute("shipping", shipping);

		return "shipping";
	}

	@GetMapping("/updateshipping")
	public String showUpdateShippingPage(Model model, @RequestParam String trackingNumber) {

		Shipping shipping = shipRepo.findByShippingTrackingNumber(trackingNumber);
		ShippingDTO shippingDTO = new ShippingDTO();
		BeanUtils.copyProperties(shipping, shippingDTO);

		model.addAttribute("shippingDTO", shippingDTO);

		return "updateshipping";
	}

	@PostMapping("/updateshipping")
	public String showUpdateShippingPage(Model model, @Valid @ModelAttribute ShippingDTO shippingDTO,
			BindingResult result) {

		try {

			Date updateDate = new Date();

			Order order = orderRepo.findByOrderTrackingNumber(shippingDTO.getShippingTrackingNumber());

			Order newOrder = new Order();
			newOrder.setOrderNumber(order.getOrderNumber());
			newOrder.setOrderItemQuantity(order.getOrderItemQuantity());
			newOrder.setOrderComment(shippingDTO.getShippingComment());
			newOrder.setOrderUpdateDate(updateDate);
			newOrder.setOrderTrackingNumber(order.getOrderTrackingNumber());
			newOrder.setOrderItemNumber(order.getOrderItemNumber().toString());
			newOrder.setOrderDate(order.getOrderDate());
			newOrder.setOrderStatus(shippingDTO.getShippingStatus());

			orderRepo.save(newOrder);

			Shipping ship = shipRepo.findByShippingTrackingNumber(shippingDTO.getShippingTrackingNumber());

			Shipping updateShip = new Shipping();
			updateShip.setShippingNumber(ship.getShippingNumber());
			updateShip.setShippingTrackingNumber(ship.getShippingTrackingNumber());
			updateShip.setShippingDate(ship.getShippingDate());
			updateShip.setShippingUpdateDate(updateDate);
			updateShip.setShippingComment(shippingDTO.getShippingComment());
			updateShip.setShippingStatus(shippingDTO.getShippingStatus());

			shipRepo.save(updateShip);

			model.addAttribute("success", true);

		} catch (Exception ex) {
			result.addError(new FieldError("shippingDTO", "shippingTrackingNumber", ex.getMessage()));
		}

		return "updateshipping";
	}
}
