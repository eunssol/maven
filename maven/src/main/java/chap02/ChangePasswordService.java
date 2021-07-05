package chap02;

public class ChangePasswordService {
	//의존객체 필요
	private MemberDao memberDao;
	
	//생성자 말고 세터메소드로 주입을 해주고
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		
	}
	
	//비밀번호를 변경하는 기능을 하는 메소드 //키가 이메일임, 비밀번호도 필요(예전것 , 새로운것)
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email); //이 이메일로 맵에서 겟해와가지고 리턴된 맵 객체가 담길것이다..?
		if (member == null) {
			throw new MemberNotFoundException(); 
		}
		//null이 아니면?
		member.changePassword(oldPwd, newPwd); //기존비번 가져와서 같은지 비교하고 다르면 예외발생(WrongIdException), 같으면 새로운 비밀번호로 대입시키는..
	}
}
