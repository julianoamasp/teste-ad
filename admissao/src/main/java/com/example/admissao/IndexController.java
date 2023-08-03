package com.example.admissao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@GetMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index.html");
	}
	@GetMapping("/categorias")
	public ModelAndView categorias() {
		return new ModelAndView("index.html");
	}
}
