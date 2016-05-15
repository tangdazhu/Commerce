package test.controller;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.commerce.controller.CommerceController;
import com.commerce.model.Commodity;
import com.commerce.model.User;
import com.commerce.service.CommodityManager;
import com.commerce.service.UserManager;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = { TestContext.class, WebAppContext.class })
@ContextConfiguration(locations = { "classpath:testContext.xml", "classpath:applicationContext.xml" })
@WebAppConfiguration
public class CommerceControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private CommerceController commerceController;

	@Autowired
	private UserManager userManager;

	@Autowired
	private CommodityManager commodityManager;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private User sellerUser;

	private User buyerUser;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		buyerUser = userManager.getUserByEmail("buyer@sina.com");
		sellerUser = userManager.getUserByEmail("seller@sina.com");
	}

	@Test
	public void commodityDetails() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();
		ModelAndView m = this.commerceController.commodityDetails(1, null, request);
		assertEquals("commodityDetails", m.getViewName());
	}

	@Test
	public void commodityBuy() throws Exception {
		MockHttpSession mockHttpSession = new MockHttpSession();
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.getSession().setAttribute("user", buyerUser);
		ModelAndView m = this.commerceController.commodityBuy(1, null, request);
		assertEquals("success", m.getViewName());
	}

	@Test
	public void updateCommodity() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("id", "1");
		request.addParameter("name", "test");
		request.addParameter("description", "testDescription");
		ModelAndView m = this.commerceController.updateCommodity(null, request);
		Commodity c = commodityManager.getCommodityById(1);
		String s = c.getDescription();
		assertEquals("testDescription", s);
		assertEquals("success", m.getViewName());

	}

	@Test
	public void loginSubmitSeller() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();

		ModelAndView m = this.commerceController.loginSubmit(sellerUser, null, request);
		assertEquals("commodityList", m.getViewName());

	}

	@Test
	public void loginSubmitBuyer() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();

		ModelAndView m = this.commerceController.loginSubmit(buyerUser, null, request);
		assertEquals("commodityListBuyer", m.getViewName());

	}

	@After
	public void verify() {
		Mockito.reset();
	}

}
