package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.TestService;

import java.util.List;

@Controller
public class TestController {

	@Autowired
	TestService test_service;

	@RequestMapping("/")
	public String sido_list(Model model) {

		List<String> list = test_service.sido_list();

		model.addAttribute("list", list);

		return "test";
	}
	
	
}
