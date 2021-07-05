package chap09;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
	
	//하나의 요청마다 따로따로 메소드 만들기
	
	@RequestMapping("/member/form.do")
	public String form(MemberVo vo, @CookieValue(value="cookieEmail", required = false) Cookie cookie) { //cookieEmail라는 이름의 객체를 cookie에다가 담아준다?
		if (cookie != null) {
			vo.setEmail(cookie.getValue());
			vo.setCheckEmail("check");
			
		}
		return "member/form";	//MvcConfig에 설정해놔서 form.jsp라고 쓰면 form.jsp.jsp가 되어버림
	}
	
	@RequestMapping("/member/login.do") // 이메일이랑pwd  파라미터 2개를 받아야햇! 파라미터를 받는 방법 3가지 중에 ~골라서 쓰면 돼
	//하나일 때는 Requestparam이 ㅍ ㅕㄴ해
	//이름을 다 똑같이 맞춰놨기 때문에 커넥션객체사용
	public String login(MemberVo vo, HttpSession sess, HttpServletRequest req,HttpServletResponse res) throws Exception { 
		MemberVo m = service.login(vo);
		if (m != null) {
			//세션에 저장
			//세션객체를 가져오는 방법
			//톰캣 서버에서 관리하는 객체이기 때문에 new로 생성 못 하고
			//1. HttpServletRequest를 통해
			//HttpSession session = req.getSession(); 
			//getSession(true) : 세션이 존재하지 않으면 새로 생성 
			//getSession(false) :세션이 존재하지 않으면 null을 리턴
			//2. 매개변수로 HttpSession -스프링이 알아서 담아준대
			
			//세션에 저장
			//세션은 서버에 저장!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!해야함
			sess.setAttribute("memberInfo",m); //"memberInfo"라는 이름으로 m을 담아
			//쿠키에 저장
			Cookie cookie = new Cookie("cookieEmail", vo.getEmail());
			cookie.setPath("/");
			if("check".equals(vo.getCheckEmail())) {
				cookie.setMaxAge(60*60*24*365); //setMaxAge(60*60*24*365) 쿠키유효시간 설정(초단위임!) + 쿠키는 삭제하는 기능이 없음 시간 0을 주는 방법뿐
			} else {
				cookie.setMaxAge(0);
			}
			res.addCookie(cookie); //여기까지가 쿠키에 저장하는 코딩~
			return "redirect:index.do"; //하나의 요청/에서만
			
			
		}else { //m이 null이다
			res.setContentType("text/html;charset=utf-8"); //응답할 때 한글 안 깨지게 
			PrintWriter out = res.getWriter(); //
			out.println("<script>");
			out.println("alert('이메일과 비밀번호가 올바르지 않습니다.');"); 
			out.println("location.href='form.do';");
			out.println("</script>");
			return null;
		}
	}
	@RequestMapping("/member/logout.do")
	public String logout(Model model, HttpSession sess, HttpServletResponse res) throws Exception  {
		sess.invalidate();
		model.addAttribute("msg","로그아웃되었습니다.~~");
		model.addAttribute("url","index.do");
		return "include/alert";
		
		
	}
	
	@RequestMapping("/member/mypage.do")
	public String logout(Model model, HttpSession sess) throws Exception  {
		//세션에서 MemberVo객체를 가져오기
		MemberVo vo = (MemberVo)sess.getAttribute("memberInfo"); //vo에는 mno, mname, email, cre_date가 담겨있음
		if (vo!=null) {
			
			MemberVo m = service.mypage(vo.getMno()); //select한 결과가 들어있을 거임
			model.addAttribute("vo",m); //"vo"라는 이름으로 m을 담아
			return "member/mypage";
		} else {
			model.addAttribute("msg","로그인 후 사용가능합니다ㅑ.");
			model.addAttribute("url","index.do");
			return "include/alert";  //jsp리턴
			
		}
	}
	
	//이렇게 해도 되긴했음!!!!
//	@RequestMapping("/member/update.do")
//	public String update(MemberVo vo, HttpSession sess, HttpServletRequest req,HttpServletResponse res) throws Exception { 
//		 service.update(vo);
//		 return "redirect:index.do"; //url리턴

	
	@RequestMapping("/member/update.do")
		 public String update(Model model, MemberVo vo, HttpSession sess) throws Exception { 
			int r = service.update(vo);
			if(r==0) {
				model.addAttribute("msg","수정실패");
				model.addAttribute("url","mypage.do");
				return "include/alert";
			} else {
				model.addAttribute("msg","정상적으로 수정되었습니다.");
				model.addAttribute("url","index.do");
				return "include/alert";
			}
	}
	@RequestMapping("/member/write.do")
	 public String write(MemberVo vo) throws Exception {
		return "member/write";
		
	}
	@RequestMapping("/member/insert.do")
	public String insert(MemberVo vo, HttpServletRequest req) throws Exception {
		if (service.insert(vo, req) ==0) {
			req.setAttribute("msg", "등록 오류");
			req.setAttribute("url", "write.do");
		}else {
			req.setAttribute("msg", "등록 성공");
			req.setAttribute("url", "index.do");
		}
		return "include/alert";
		
	}
	

		
		
	}

