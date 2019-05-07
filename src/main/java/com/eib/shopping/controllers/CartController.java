package com.eib.shopping.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.eib.shopping.data.DataAccess;


@Controller
@RequestMapping(value = {"cart", "/cart", "/cart/"})
public class CartController {
	
	 @RequestMapping(value = "index", method = RequestMethod.GET) 
	 public ModelAndView index(HttpServletRequest request, HttpServletResponse response) { 
		 
		ModelAndView mav = new ModelAndView("cart/list_cart_products");
		mav.addObject("userName", DataAccess.sesions.get(request.getSession()));
		mav.addObject("cartList", DataAccess.listItems(DataAccess.sesions.get(request.getSession()))); 
				
		return mav;
	 }
	 
	@RequestMapping(value = "cart", method = RequestMethod.GET)
	public ModelAndView buy(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("cart/list_cart_products");
		mav.addObject("userName", DataAccess.sesions.get(request.getSession()));
		mav.addObject("cartList", DataAccess.listItems(DataAccess.sesions.get(request.getSession()))); 
			
		return mav;
	}
	
	@RequestMapping(value = "add/{id}", method = RequestMethod.GET)
	public ModelAndView add(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
		
		DataAccess.cartClient.get(DataAccess.sesions.get(request.getSession())).get(id).addQuantity();
		
		ModelAndView mav = new ModelAndView("redirect:/cart/index");
		mav.addObject("userName", DataAccess.sesions.get(request.getSession()));
		mav.addObject("cartList", DataAccess.listItems(DataAccess.sesions.get(request.getSession()))); 
			
		return mav;
	}
	
	@RequestMapping(value = "minus/{id}", method = RequestMethod.GET)
	public ModelAndView minus(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
		
		DataAccess.cartClient.get(DataAccess.sesions.get(request.getSession())).get(id).minusQuantity();
		
		ModelAndView mav = new ModelAndView("redirect:/cart/index");
		mav.addObject("userName", DataAccess.sesions.get(request.getSession()));
		mav.addObject("cartList", DataAccess.listItems(DataAccess.sesions.get(request.getSession()))); 
			
		return mav;
	}
	
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	public ModelAndView remove(@PathVariable("id") String id,HttpServletRequest request, HttpServletResponse response) {
		
		DataAccess.cartClient.get(DataAccess.sesions.get(request.getSession())).remove(id);
		
		ModelAndView mav = new ModelAndView("redirect:/cart/index");
		mav.addObject("userName", DataAccess.sesions.get(request.getSession()));
		mav.addObject("cartList", DataAccess.listItems(DataAccess.sesions.get(request.getSession())) ); 		
		
		return mav;
	}
}

