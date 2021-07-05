package chap07;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {//MemberServiceImpl이 객체를 사용할 곳은 Controller
	
	@Autowired
	MemberDao dao;

	@Override
	public List<MemberVo> selectList() {
		
		return dao.selectList();
	}

}
