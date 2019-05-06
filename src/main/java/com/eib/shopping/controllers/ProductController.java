package com.eib.shopping.controllers;

import java.io.OutputStream;
import java.util.Base64;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eib.shopping.Item;
import com.eib.shopping.ProductModel;
import com.eib.shopping.ZXingHelper;
import com.eib.shopping.beans.ClientBean;
import com.eib.shopping.data.DataAccess;

@Controller
@RequestMapping(value ="product")
public class ProductController {

	
	@RequestMapping(value = "index", method = RequestMethod.GET) 
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) { 
		
		ProductModel productModel = new ProductModel();
		
		ModelAndView mav = new ModelAndView("products/index");
		mav.addObject("products", productModel.findAll());
		//mav.addObject("userName", DataAccess.nameUser);
		mav.addObject("userName", DataAccess.sesions.get(request.getSession()));
		mav.addObject("itemsInCart", DataAccess.itemsInCart(DataAccess.sesions.get(request.getSession()))); 
		return mav; 
	}
	 
	
	@RequestMapping(value = "buy/{id}", method = RequestMethod.GET)
	public ModelAndView buy(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
		
		ProductModel productModel = new ProductModel();
		
		//String user = DataAccess.activeUser;
		String user = DataAccess.sesions.get(request.getSession());
		
		if(user!=null) {
			if(DataAccess.cartClient.get(user) == null) {
				DataAccess.cartClient.put(user, new HashMap<String,Item>());
				DataAccess.cartClient.get(user).put(id, new Item(productModel.find(id),1)); 
			}else {
				if(DataAccess.cartClient.get(user).get(id)!=null)
					DataAccess.cartClient.get(user).get(id).setQuantity(DataAccess.cartClient.get(user).get(id).getQuantity() + 1);
				else {
					DataAccess.cartClient.get(user).put(id, new Item(productModel.find(id),1));
				}
			}
		}
		
		ModelAndView mav = new ModelAndView("redirect:/product/index");
		mav.addObject("products", productModel.findAll());
		mav.addObject("userName", user);
		mav.addObject("itemsInCart", DataAccess.itemsInCart(user)); 
		
		return mav;
	}
	
	@RequestMapping(value = "barcode/{id}", method = RequestMethod.GET)
	public void barcode(@PathVariable("id") String id, HttpServletResponse response) throws Exception {
	
        response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(ZXingHelper.getBarCodeImage(id, 2000, 200));
		outputStream.flush();
		outputStream.close();
	}
	
}
