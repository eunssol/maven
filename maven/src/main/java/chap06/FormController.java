package chap06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// 이걸 안 넣으면 빈에 등록이 안 돼 . ComponentScan했기 때문에 싹 다 빈에 등록됨 dao는 @Repaseitory
// service는 @service
//이걸 넣는 이유는 ? 빈에 등록하려고     
//빈에 등록이 되어있어야만, 자동으로.....
//handlerController이 찾아줘 
//디스패쳐서블릿이 FormController에서 앞글자만 소문자로 바꿔서 빈에 등록을 한다? 대박

@Controller 
public class FormController {

//	@RequestMapping("/index.do")
//	public String index() {
//		return "index"; 
//	} 
	//이렇게 컨트롤러 맵핑 안 해도 addViewControllers 추가해서 (MvcConfig.java에 있어~ 확인해봐)
	
	
	// 리턴이 없으면 매핑된 url과 동일한 경로 jsp를 포워딩
	// 서블릿으로 응답도 가능
	@RequestMapping("/test.do")
	public void test(HttpServletResponse res) throws IOException { //간단한 내용이라면 굳이 jsp안 만들고 이렇게 서블릿 통해서도 가능하대
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>alert('정상적으로 저장되었습니다.');</script>");
	}
	
	
	@RequestMapping("/")
	public String index() {
		return "redirect:index.do"; //요청이 한번 더 일어나 새로운 요청이 일어남. 
	} //index.do페이지로 이동 // 디스패쳐설정에 예를 들어, .do로 되어있으면  
	
	
	@RequestMapping("/form.do")// "/form.do" 이 주소를 요청하면 form 메소드 실행
	public String form() {
		return "form"; // 입력폼만 나오면 됨. form.jsp만 포워딩하는 역할. 아무런 로직이 없음 기능이 없음 //얘는 하나의 요청
	}
	
	
	@RequestMapping("/send.do")
	public String send(
			Model model, 
			HttpServletRequest req,
			@RequestParam(value = "name" , required = false) String name2, //name이 null일 때 에러가 나서, 없어도 에러가 안 나게 하기 위해서 required~
			@RequestParam(value = "email", required = false) String email2,
			@RequestParam(value="no", required = false, defaultValue="0") int no,
			MemberVo vo
			
			) {
		// 파라미터를 받는 방법
		// 1번째 : HttpServletRequest 사용
		// 할 거임 이거 사용하려면 객체 필요함 객체는 어디있음?/ 톰캣에 있음 톰캣에서 받아와야함 근데 스프링이 ㅇ알ㅇ아서 해줌
		// 매개변수로 HttpServletRequest 넣어주면 알아서 됨
		String name = req.getParameter("name"); //폼에 있던 이름 넣어주면 가져온다 이것은 form.jsp에 있던 이름.
		String email = req.getParameter("email");
		// 2번째 방법 : @RequestParam을 쓰는 방법
		//			@RequestParam("파라미터명") : 매개변수에 지정
		//@RequestParam 역할이 name2에 "name"이란 이름을 넣어주는 거야! 근데 여기에 값이 몇개 있을지 모르고, 빈문자열도 넘어갈 때 오류가 나지않게
		//하기 위해서 required = false라고 해준다! 
		//required 무조건 입력해라~~ 필수! false니까 안 해도 됨 
		
		// 3번째 방법 : 커맨드객체 
		
		System.out.println(vo.getHobby().length);
		for (int v: vo.getHobby()) {
			System.out.println(v);
		}
		
		
		model.addAttribute("name1", name);
		model.addAttribute("email1", email);
		model.addAttribute("name2", name2);
		model.addAttribute("email2", email2);
		model.addAttribute("no", no);
		model.addAttribute("vo", vo); //멤버vo객체가 "vo"이름으로 맵에 들어감
		
		req.setAttribute("id", "hong");
		//request에 set을 하던, model에 add를 하던, jsp입장에선 다 똑같아 
		
		if(email ==null || "".equals(email)) {
			return "form"; //포워딩을 다른 곳으로 하게끔. url따라서 무조건 jsp랑 1대1매칭 ㄴㄴ 컨트롤러로 제어하는 거야 
		}
		return "send";
	}
	
	//ModelAndView 객체 리턴
	@RequestMapping("/main.do")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", "홍길동");
		mav.setViewName("main");
		return mav;
		
	}
}

