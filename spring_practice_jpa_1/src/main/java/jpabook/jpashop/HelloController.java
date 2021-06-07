package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	
	@GetMapping("/hello")
	public String hello(Model model) {
		Hello hello = new Hello();
		hello.setData("hello world");
		String data = hello.getData();
		model.addAttribute("data",data);
		
		return "hello";
	}

}
