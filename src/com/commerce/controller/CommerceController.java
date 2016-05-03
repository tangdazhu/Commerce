package com.commerce.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.UserTransaction;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.commerce.model.Commodity;
import com.commerce.model.Transaction;
import com.commerce.model.User;
import com.commerce.service.CommodityManager;
import com.commerce.service.SendMsgManager;
import com.commerce.service.TransManager;
import com.commerce.service.UserManager;
import com.commerce.util.Constants;
import com.commerce.view.CommodityListExcelView;
import com.commerce.view.CommodityListJsonView;

import nl.captcha.Captcha;

@EnableWebMvc
@RestController
// @ImportResource("classpath:applicationContext.xml")
public class CommerceController {

	@Autowired(required = true)
	private UserManager userManager;

	@Autowired(required = true)
	private CommodityManager commodityManager;

	@Autowired(required = true)
	private TransManager transManager;

	@Autowired(required = true)
	private SendMsgManager sendMsgManager;

	@Resource(name = "JtaTransactionManager")
	private JtaTransactionManager txManager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("userForm") User user) throws Exception {

		ModelAndView mv = new ModelAndView();

		mv.setViewName("login");
		return mv;
	}

	@RequestMapping(value = "/commodityBuy/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	@Transactional(value = "JtaTransactionManager", propagation = Propagation.REQUIRED)
	public ModelAndView commodityBuy(@PathVariable("id") int id, Model model, HttpServletRequest request)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		UserTransaction userTx = this.txManager.getUserTransaction();
		try {
			userTx.begin();

			User user = (User) request.getSession().getAttribute("user");
			Commodity c = commodityManager.getCommodityById(id);
			Transaction t = new Transaction();
			t.setAddress("my_address");
			t.setCommodity(c);
			t.setUser(user);
			transManager.insertTrans(t);

			// put message queue
			Queue queue = new ActiveMQQueue("CommodityOrderQueue");

			this.sendMsgManager.putMessage(queue);

			mv.setViewName("success");
			userTx.commit();
			return mv;

		} catch (Exception e) {
			userTx.rollback();
			throw e;
		}
	}

	@RequestMapping(value = "/updateCommodity", method = { RequestMethod.POST })
	public ModelAndView updateCommodity(Model model, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		int id = this.getIntValueFromParam("id", request);
		String name = request.getParameter("name");
		String desp = request.getParameter("description");
		Commodity c = commodityManager.getCommodityById(id);
		c.setName(name);
		c.setDescription(desp);
		commodityManager.updateCommodity(c);
		mv.addObject("commodity", c);
		mv.setViewName("success");
		return mv;
	}

	@RequestMapping(value = "/commodityDetails/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView commodityDetails(@PathVariable("id") int id, Model model, HttpServletRequest request)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		Commodity c = commodityManager.getCommodityById(id);
		mv.addObject("commodity", c);
		mv.setViewName("commodityDetails");
		return mv;
	}

	@RequestMapping(value = "/commodityList", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView loginSubmit(@ModelAttribute("userForm") User user, Model model, HttpServletRequest request)
			throws Exception {

		ModelAndView mv = new ModelAndView();

		String email = user.getEmail();
		String password = user.getPassword();
		String suffix = getRequestSuffix(request);
		String type = "seller";

		if (suffix.equals("")) {
			User dbuser = userManager.getUserByEmail(email);

			Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
			request.setCharacterEncoding("UTF-8");
			String answer = request.getParameter("answer");
			if ((answer != null) && ((captcha != null)) && !captcha.isCorrect(answer)) {
				mv.setViewName("login");
				return mv;
			}

			if (dbuser == null || !password.equals(dbuser.getPassword())) {
				mv.setViewName("login");
				return mv;
			}
			type = dbuser.getType();
			request.getSession().setAttribute("user", dbuser);
		}
		List l = null;
		String attributeName = "commodityList";

		if (type.equals("buyer"))
			mv.setViewName("commodityListBuyer");
		else
			mv.setViewName("commodityList");

		int pageNumber = getIntValueFromParam("pageNumber", request);

		int pageSize = getIntValueFromParam("pageSize", request);
		int totalPage = 1;

		if (pageNumber == 0) {
			l = commodityManager.listAllCommodities();
			pageNumber = 1;
		} else {
			if (pageSize == 0)
				pageSize = Constants.pageSize;

			l = commodityManager.listCommoditiesByPage(pageNumber, pageSize);

			int total = commodityManager.getSizeOfCommodities("");
			totalPage = (int) Math.ceil(total / pageSize);
		}
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPage", totalPage);

		model.asMap().clear();
		deliverViewOnSuffix(mv, attributeName, l, suffix);

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
			mv.addObject(attributeName, attributeValue);

		} else if (suffix.equals("json")) {
			mv.addObject(attributeName, attributeValue);
			CommodityListJsonView view = new CommodityListJsonView();
			mv.setView(view.getView());
		} else if (suffix.equals("xls")) {
			CommodityListExcelView view = new CommodityListExcelView(attributeValue);
			mv.setView(view);
		} else if (suffix.equals("xml")) {
		}

	}

	/*
	 * Only handle MyException, the others will go for GlobalDefaultException
	 */
	@ExceptionHandler(MyException.class)
	public ModelAndView handleAllException(HttpServletRequest req, Exception exception) {

		ModelAndView mav = new ModelAndView("exception");
		java.util.Date date = new java.util.Date();

		mav.addObject("timestamp", new Timestamp(date.getTime()));
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("myexception");

		return mav;

	}

	private int getIntValueFromParam(String param, HttpServletRequest request) {
		int a = 0;
		String aString = request.getParameter(param);
		if (aString != null) {
			Integer ia = Integer.valueOf(aString);
			a = ia.intValue();
		}
		return a;

	}

}
