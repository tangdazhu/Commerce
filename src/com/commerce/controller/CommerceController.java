package com.commerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.commerce.model.User;
import com.commerce.service.CommodityManager;
import com.commerce.service.UserManager;
import com.commerce.view.CommodityListExcelView;
import com.commerce.view.CommodityListJsonView;

@RestController
// @ImportResource("classpath:applicationContext.xml")
public class CommerceController {

	@Autowired(required = true)
	private UserManager userManager;

	@Autowired(required = true)
	private CommodityManager commodityManager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("userForm") User user) throws Exception {

		ModelAndView mv = new ModelAndView();

		mv.addObject("message", "	Hello World!");

		mv.setViewName("login");
		return mv;
	}

	@RequestMapping(value = "/commodityList", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView loginSubmit(@ModelAttribute("userForm") User user, Model model, HttpServletRequest request,SessionStatus status,WebRequest webRequest)
			throws Exception {

		ModelAndView mv = new ModelAndView();

		String suffix = getRequestSuffix(request);

		String email = user.getEmail();
		String password = user.getPassword();

		User dbuser = userManager.getUserByEmail(email);

		List l = commodityManager.listAllCommodities();

		String attributeName = "commodityList";
		Object attributeValue = l;

		if (dbuser != null && password.equals(dbuser.getPassword())) {

			mv.setViewName("commodityList");

		} else {
			
			mv.setViewName("login");
		}
		model.asMap().clear();
		deliverViewOnSuffix(mv, attributeName, attributeValue, suffix);

		return mv;
	}

	private String getRequestSuffix(HttpServletRequest request) {
		String suffix = "";
		String requestURI = request.getRequestURI();
		String[] s = requestURI.split("\\.");
		if (s.length > 1) {
			suffix = s[1];
		}
		return suffix;

	}

	private void deliverViewOnSuffix(ModelAndView mv, String attributeName, Object attributeValue, String suffix)
			throws Exception {

		if (suffix.equals("")) {
			mv.addObject(attributeValue);
		} else if (suffix.equals("json")) {

			
			mv.addObject(attributeValue);

			CommodityListJsonView view = new CommodityListJsonView();
			mv.setView(view.getView());
		} else if (suffix.equals("xls")) {
			CommodityListExcelView view = new CommodityListExcelView(attributeValue);
			mv.setView(view);
		} else if (suffix.equals("xml")) {
		}

	}

}
