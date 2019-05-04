package com.eib.shopping.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eib.shopping.Item;
import com.eib.shopping.Product;
import com.eib.shopping.ProductModel;
import com.eib.shopping.beans.ClientBean;
import com.eib.shopping.beans.LoginBean;
import com.sun.javafx.collections.MappingChange.Map;

@Controller
@RequestMapping(value = {"product", "/*", "/buy", "/buy/"})
public class AccessControl {
	private HashMap<String, String> controlAccess = new HashMap<String, String>();
	private HashMap<String, HashMap<String, Item>> cartClient = new HashMap<String, HashMap<String,Item>>();
	private String activeUser = null;
	private String nameUser = null;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("login") LoginBean login) {
		
		ModelAndView mav = null;
		
		controlAccess.put("Enrique", "12345");
		
		if(controlAccess.containsKey(login.getUserName()) && (controlAccess.get(login.getUserName()).equals(login.getPassword()))) {
			mav = new ModelAndView("/products/index");
			ProductModel productModel = new ProductModel();
			mav.addObject("products", productModel.findAll());
			//mav.addObject("itemsInCart", itemsInCart(login.getUserName())); 
			activeUser=login.getUserName();
			nameUser=login.getUserName();
					
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
		
		controlAccess.put(client.getUser(), client.getPassword());
		
		ModelAndView mav = null;
		
		mav = new ModelAndView("/products/index");
		ProductModel productModel = new ProductModel();
		mav.addObject("products", productModel.findAll());
		mav.addObject("userName", client.getName());
		//mav.addObject("itemsInCart", itemsInCart(client.getUser())); 
		activeUser=client.getUser();
		nameUser = client.getName();
		
		return mav;
	}
	
	@RequestMapping(value = "buy/{id}", method = RequestMethod.GET)
	public ModelAndView buy(@PathVariable("id") String id, HttpServletResponse response) {
		
		System.out.printf("Hola");
		
		ProductModel productModel = new ProductModel();
		
		if(activeUser!=null) {
			if(cartClient.get(activeUser) == null) {
				cartClient.put(activeUser, new HashMap<String,Item>());
				cartClient.get(activeUser).put(id, new Item(productModel.find(id),1)); 
			}else {
				if(cartClient.get(activeUser).get(id)!=null)
					cartClient.get(activeUser).get(id).setQuantity(cartClient.get(activeUser).get(id).getQuantity() + 1);
				else {
					cartClient.get(activeUser).put(id, new Item(productModel.find(id),1));
				}
			}
		}
		
		ModelAndView mav = new ModelAndView("products/index");
		mav.addObject("products", productModel.findAll());
		mav.addObject("userName", nameUser);
		mav.addObject("itemsInCart", itemsInCart(activeUser, id)); 
			
		return mav;
	}
	
	public int itemsInCart(String user, String id) {
		int itemsTotales = 0;
	
		if(cartClient.containsKey(user)) {
			itemsTotales+= cartClient.get(user).size();
			itemsTotales+= cartClient.get(user).get(id).getQuantity();
			return itemsTotales;
		}
		return 0;
	}
}


