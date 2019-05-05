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
@RequestMapping(value = {"product", "/buy", "/buy/"})
public class ProductController {

	
	@RequestMapping(value = "index", method = RequestMethod.GET) 
	public ModelAndView index() { 
		
		ProductModel productModel = new ProductModel();
		
		ModelAndView mav = new ModelAndView("products/index");
		mav.addObject("products", productModel.findAll());
		mav.addObject("userName", DataAccess.nameUser);
		mav.addObject("itemsInCart", DataAccess.itemsInCart(DataAccess.activeUser)); 
		return mav; 
	}
	 
	
	@RequestMapping(value = "buy/{id}", method = RequestMethod.GET)
	public ModelAndView buy(@PathVariable("id") String id, HttpServletResponse response) {
		
		ProductModel productModel = new ProductModel();
		
		if(DataAccess.activeUser!=null) {
			if(DataAccess.cartClient.get(DataAccess.activeUser) == null) {
				DataAccess.cartClient.put(DataAccess.activeUser, new HashMap<String,Item>());
				DataAccess.cartClient.get(DataAccess.activeUser).put(id, new Item(productModel.find(id),1)); 
			}else {
				if(DataAccess.cartClient.get(DataAccess.activeUser).get(id)!=null)
					DataAccess.cartClient.get(DataAccess.activeUser).get(id).setQuantity(DataAccess.cartClient.get(DataAccess.activeUser).get(id).getQuantity() + 1);
				else {
					DataAccess.cartClient.get(DataAccess.activeUser).put(id, new Item(productModel.find(id),1));
				}
			}
		}
		
		ModelAndView mav = new ModelAndView("products/index");
		mav.addObject("products", productModel.findAll());
		mav.addObject("userName", DataAccess.nameUser);
		mav.addObject("itemsInCart", DataAccess.itemsInCart(DataAccess.activeUser)); 
			
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
