package chap04;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberRegisterService { //로직처리를 위한 클래스
	@Autowired
	private MemberDao memberDao; //필드 선언 MemberDao가 필요해서
	
//	public MemberRegisterService(MemberDao memberDao) {//생성자
//		this.memberDao = memberDao;
//		//memberDao를 매개변수로 외부에서 얘를 주입받아가지고 memberDao객체에다가 넣어주는 상태로..........
		//memberDao를 외부에서 주입해줄 수 있도록 
		//미리 만들어놓고 그 객체를 주입해주자.
		// this.memberDao =new MemberDao(); 이케 해주면 계속계속 생성되고 처리해줘야하니까
//	}
	
	//호출할 실행할 메소드 
	//코멘드객체라는 개념 때문에 Member를 안 넣고 RegisterRequest req를 넣었어....
	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail()); 
		if (member != null) {
			throw new DuplicateMemberException("이메일 중복:"+req.getEmail());
		}
		//id는 알아서 ++되니까 생성자에 넣어줄 필요 없음
		Member newMember = new Member(
				req.getEmail(), 
				req.getPassword(), 
				req.getName(),
				LocalDateTime.now());
			memberDao.insert(newMember);
			return newMember.getId(); //이메일을 가지고 중복확인 하고 중복이 안 돼있으면 추가계정의 아이디를 리턴해주는...맞나
			
		}
		
		
	}


