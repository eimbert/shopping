package com.eib.shopping.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.eib.shopping.Item;

public class DataAccess {
	public static HashMap<String, String> controlAccess = new HashMap<String, String>();
	public static HashMap<String, HashMap<String, Item>> cartClient = new HashMap<String, HashMap<String,Item>>();
	public static HashMap<HttpSession, String> sesions = new HashMap<HttpSession, String>();
	
	public static String activeUser = null;
	public static String nameUser = null;
	
	public static int itemsInCart(String user) {
		int itemsTotales = 0;
	
		if(DataAccess.cartClient.containsKey(user)) {
			
			Set<String>  i= DataAccess.cartClient.get(user).keySet();
			for(String clave: i) {
				itemsTotales+= DataAccess.cartClient.get(user).get(clave).getQuantity();
			}
			return itemsTotales;
		}
		return 0;
	}
	
	public static List<Item> listItems(String user){
		List<Item> cartItems = new ArrayList<Item>();
		Set<String>  i= DataAccess.cartClient.get(user).keySet();
		for(String clave: i) {
			 cartItems.add(DataAccess.cartClient.get(user).get(clave));
		}
		
		return cartItems;
	}
}
