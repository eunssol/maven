package chap09;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {// MemberServiceImpl이 객체를 사용할 곳은 Controller

	@Autowired
	MemberDao dao;

	@Override
	public List<MemberVo> selectList() {

		return dao.selectList();
	}

	@Override
	public MemberVo login(MemberVo vo) { // 서비스가 지금 해야할 일들 : email, pwd로 조회 -- dao만 호출하면 돼

		return dao.login(vo);
	}

	@Override
	public MemberVo mypage(int mno) {

		return dao.selectOne(mno);

	}

	@Override
	public int update(MemberVo vo) {
		
		return dao.update(vo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Throwable.class}) //트랜잭션 걸었어. 
	public int insert(MemberVo vo, HttpServletRequest req){
		int r = 1;
		
			
		dao.insert(vo); // members테이블에 등록이 됨
		// vo객체에 mno가 set이 되어있는 상태

		String[] school = req.getParameterValues("school");
		String[] year = req.getParameterValues("year");

		Map map = new HashMap();
		map.put("members_mno", vo.getMno());
		for (int i = 0; i < school.length; i++) {
			if (!"".equals(school[i]) || !"".equals(year[i])) {
				map.put("school", school[i]);
				map.put("year", school[i]);
				dao.insertSchool(map);
			}
		}
	
		
		return r;
		
	}
}

	/*
	 * @Override public void update(MemberVo vo) { dao.update(vo);
	 * 
	 * }
	 */


//	@Override
//	public String mypage(HttpSession sess, Model model) {
//		MemberVo vo = (MemberVo) sess.getAttribute("memberInfo");
//		if (vo != null) {
//			MemberVo m = dao.selectOne(vo.getMno()); // select한 결과가 들어있을 거임
//			model.addAttribute("vo", m); // "vo"라는 이름으로 m을 담아
//			return "member/mypage";
//		} else {
//			model.addAttribute("msg", "로그인 후 사용가능합니다.");
//			model.addAttribute("url", "index.do");
//			return "include/alert";
//		}
//	}

