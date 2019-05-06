package com.eib.shopping.controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.eib.shopping.Item;
import com.eib.shopping.ProductModel;
import com.eib.shopping.beans.ClientBean;
import com.eib.shopping.beans.LoginBean;
import com.eib.shopping.data.DataAccess;


@Controller
@RequestMapping(value = {"product", "/*", "/buy", "/buy/"})
public class AccessControl {
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("login") LoginBean login) {
		
		ModelAndView mav = null;
		
		//DataAccess.controlAccess.put("Enrique", "12345");
		
		if(DataAccess.controlAccess.containsKey(login.getUserName()) && (DataAccess.controlAccess.get(login.getUserName()).equals(login.getPassword()))) {
			mav = new ModelAndView("/products/index");
			ProductModel productModel = new ProductModel();
			mav.addObject("products", productModel.findAll());
			mav.addObject("itemsInCart", DataAccess.itemsInCart(login.getUserName())); 
			DataAccess.sesions.put(request.getSession(), login.getUserName());
			DataAccess.activeUser=login.getUserName();
			DataAccess.nameUser=login.getUserName();
					
			return mav;
		}else {
			mav = new ModelAndView("/login/login");
			mav.addObject("msg", "Nombre de usuario o contraseña incorrecta");
			return mav;
		}	
        
	}
	
	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public ModelAndView newUser(HttpServletRequest request, HttpServletResponse response) {
				
		ModelAndView mav = new ModelAndView("/login/newuser");
		mav.addObject("newUser", new ClientBean());
		
		return mav;
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ModelAndView addNewUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("newUser") ClientBean client) {
		
		DataAccess.controlAccess.put(client.getUser(), client.getPassword());
		
		ModelAndView mav = null;
		
		mav = new ModelAndView("/products/index");
		ProductModel productModel = new ProductModel();
		mav.addObject("products", productModel.findAll());
		mav.addObject("userName", client.getName());
		mav.addObject("itemsInCart", DataAccess.itemsInCart(client.getUser())); 
		DataAccess.sesions.put(request.getSession(), client.getUser());
		DataAccess.activeUser=client.getUser();
		DataAccess.nameUser = client.getName();
		
		return mav;
	}

}


