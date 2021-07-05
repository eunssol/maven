package chap06;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

	@RequestMapping(value="/hello.do", method=RequestMethod.GET) //get방식으로만 접속 간응ㅋGetmapping이랑 똑같아 굳이 이렇게 쓸 필요 없지
	public String hello(Model model) {
		model.addAttribute("name1", "홍길동"); // 디스패쳐에서 이 이름으로 등록한 거임
		model.addAttribute("name2", "김길동"); // 디스패쳐에서 이 이름으로 등록한 거임

		// 배열로 하든가, 객체?

		return "hello"; // WEB-INF view에다가 폴더없이 jsp이름이 반드시 hello라는 이름으로
	}

	@PostMapping("/hello2.do")
	public String hello2(Model model) {
		model.addAttribute("name", "홍길동"); // 디스패쳐에서 이 이름으로 등록한 거임
		return "hello";
	}
	@GetMapping("/board/write.do")
	public String write(Model model) {
		model.addAttribute("name", "홍길동"); // 디스패쳐에서 이 이름으로 등록한 거임
		return "hello";
	}
	@PostMapping("/board/write2.do")
	public String write2(Model model) {
		model.addAttribute("name", "홍길동"); // 디스패쳐에서 이 이름으로 등록한 거임
		return "hello";
	}
}