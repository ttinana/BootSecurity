package com.example.ws.web.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

   

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    protected ModelAndView handleRequestHi(HttpServletRequest request, HttpServletResponse response) throws Exception {

	ModelAndView modelandview = new ModelAndView("index");
	modelandview.addObject("welcomeMessage", "CRHoV");

	return modelandview;
    }
    
    @RequestMapping("/hello")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World")
    String name, Model model) {
	model.addAttribute("name", name);
	return "index";
    }

}
