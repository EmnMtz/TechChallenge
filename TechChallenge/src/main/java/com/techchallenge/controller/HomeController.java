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
import org.springframework.web.bind.annotation.GetMapping;

import com.techchallenge.model.Inventory;
import com.techchallenge.model.Order;
import com.techchallenge.model.OrderDTO;
import com.techchallenge.model.OrderDisplay;
import com.techchallenge.repository.InventoryRepo;
import com.techchallenge.repository.OrderRepo;

@Controller
public class HomeController {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private InventoryRepo invRepo;

	@GetMapping({ "", "/" })
	public String showIndexPage() {
		return "index";
	}

	@GetMapping({ "", "/home" })
	public String showHomePage(Model model) {

		List<OrderDisplay> orders = new ArrayList<OrderDisplay>();

		List<Order> orderList = orderRepo.findAll();

		List<Inventory> inventory = invRepo.findAll();

		try {
			for (Order ord : orderList) {

				OrderDisplay order = new OrderDisplay();

				BeanUtils.copyProperties(ord, order);

				Inventory item = inventory.get(Integer.valueOf(ord.getOrderItemNumber()) - 1);

				order.setOrderItemDescription(item.getItemDescription());

				String pattern = "yyyy-MM-dd";
				DateFormat df = new SimpleDateFormat(pattern, Locale.ENGLISH);
				Date date = ord.getOrderDate();
				String currentDate = df.format(date);

				order.setOrderDate(currentDate);

				date = ord.getOrderUpdateDate();
				currentDate = df.format(date);

				order.setOrderUpdateDate(currentDate);

				OrderDTO orderDTO = new OrderDTO();

				BeanUtils.copyProperties(ord, orderDTO);
				orderDTO.setOrderItemDescription(item.getItemDescription());

				order.setOrderDTO(orderDTO);

				orders.add(order);
			}
		} catch (Exception ex) {

			ex.printStackTrace();
		}

		model.addAttribute("orders", orders);

		return "home";
	}

}
