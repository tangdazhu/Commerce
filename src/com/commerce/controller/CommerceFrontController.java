package com.commerce.controller;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.commerce.model.User;
import com.commerce.service.CommodityManager;
import com.commerce.service.UserManager;

@Controller
// @ImportResource("classpath:applicationContext.xml")
public class CommerceFrontController {

	@Autowired(required = true)
	private UserManager userManager;

	@Autowired(required = true)
	private CommodityManager commodityManager;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("userForm") User user)
			throws Exception {

		ModelAndView mv = new ModelAndView();

		mv.addObject("message", "	Hello World!");

		mv.setViewName("login");
		return mv;
	}

	@RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
	public ModelAndView loginSubmit(@ModelAttribute("userForm") User user,
			Model model) throws Exception {

		ModelAndView mv = new ModelAndView();

		String email = user.getEmail();
		String password = user.getPassword();

		User dbuser = userManager.getUserByEmail(email);

		if (dbuser != null && password.equals(dbuser.getPassword())) {

			mv.setViewName("mainpage");

			List l = commodityManager.listAllCommodities();
		
			mv.addObject("commodityList", l);
		} else {
			mv.setViewName("login");
			return mv;
		}
		;

		return mv;
	}

}
