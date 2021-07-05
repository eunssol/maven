package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //스프링의 설정파일 표시
public class AppCtx {
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService regSvc() {
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public ChangePasswordService pwdSvc() { //얘는 세터메소드로 해서 생성하고 셋해주기
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}
	
	
	//3개다 빈으로 등록
	//각각의 빈은 memberDao regSvc pwdSvc 가 이름임 메소드가 곧 빈의 이름임
	@Bean
	public MemberListService listSvc() {
		return new MemberListService(memberDao());
	}
	
	@Bean
	public MemberInfoService infoSvc() {
		return new MemberInfoService(memberDao());
	}
}
