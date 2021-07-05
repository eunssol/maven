package chap02;

public class Assembler {
	//우리가 만든 객체 총3가지 서비스2개랑 DAO
	private MemberDao memberDao;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	private MemberListService listSvc;
	private MemberInfoService infoSvc;
	
	//Assembler객체를 생성할 때 다 위에 3개 다 초기화해줄 거야 서비스 2개 DAO주입 받은 상태
	public Assembler() {
		memberDao = new MemberDao();
		
		//regSvc를 생성자를 통해서 주입
		regSvc = new MemberRegisterService(memberDao); 
		
		
		pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao);//세터메소드로 주입
		
		listSvc = new MemberListService(memberDao);
		infoSvc = new MemberInfoService(memberDao);
		
	}
	

	//게터메소드만 (다른 데서 가져다쓰려면 게터메소드 필요)
	public MemberDao getMemberDao() {
		return memberDao;
	}

	public MemberRegisterService getRegSvc() {
		return regSvc;
	}

	public ChangePasswordService getPwdSvc() {
		return pwdSvc;
	}
	public MemberListService getListSvc() {
		return listSvc;
	}
	public MemberInfoService getInfoSvc() {
		return infoSvc;
	}
}
