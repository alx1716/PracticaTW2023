package com.wikinado.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wikinado.entities.Wiki;
import com.wikinado.service.WikiService;


@Controller
public class WikisController {

	private WikiService wikiService;

    @Autowired
    public void WikiController(WikiService wikiService) {
        this.wikiService = wikiService;
    }
    
	@GetMapping("/wikis")	
	public String listWikis(Model model) {
        List<Wiki> wikis = wikiService.wikiList();
        model.addAttribute("wikis", wikis);
        return "wikis"; 
    }
}
