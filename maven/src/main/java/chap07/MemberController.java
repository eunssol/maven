package chap07;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	
	//얘도 주입을 받아야하는 객체가 있으므로
	@Autowired
	MemberService service; 
	
	//매핑시킬 주소가 있어야하니까
	@RequestMapping("/member/index.do")
	public String index(Model model) {
		List<MemberVo> list = service.selectList(); //list에 모든회원정보가 들어갈 것이여
		model.addAttribute("list", list); //"list"란 이름으로 저장
		return "member/index";
		
		
	}
	
	
	
}
