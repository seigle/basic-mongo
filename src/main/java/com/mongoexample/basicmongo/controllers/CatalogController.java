package com.mongoexample.basicmongo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mongoexample.basicmongo.dto.CatalogItem;

@Controller
public class CatalogController {

	@GetMapping("catalog")
	public String displayCatalog(Model model) {
		CatalogItem catalogItem = new CatalogItem();
		catalogItem.setName("Sample Product");
		catalogItem.setDescription("This is a product description.");
		catalogItem.setPrice(19.99);
		catalogItem.setImageUrl("/images/TBD.jpg");
		
		model.addAttribute("catalogItem", catalogItem);
		
		return "catalog";
	}
}
