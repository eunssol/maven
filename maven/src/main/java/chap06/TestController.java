package chap06;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")//상위폴더 
public class TestController {
	
	@RequestMapping("/form.do")//하위폴더
	public String form() {
		return "form";
	}
	
	@RequestMapping("/hello.do")//하위폴더
	public String hello() {
		return "hello";
	}

}
