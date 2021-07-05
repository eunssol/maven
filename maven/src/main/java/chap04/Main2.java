package chap04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 { //스프링 이용하기 때문에 assembler 필요 없어
	private static AnnotationConfigApplicationContext ctx = null; //미리 선언 하나 해놓고
	//클래스파일 넣어서 읽어오면 됨
	
	
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class); //설정파일을 읽어오면 여러개의 빈이 빈컨테이너에 등록이 될 거임. 꺼내쓰기만 하면 됨.
		
		
		// 콘솔창에서 입력을 받을 거라서 BufferedReader 사용
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) { // 무한반복
			System.out.println("명령어를 입력해 주세요.");
			String cmd = reader.readLine(); // 한줄씩 코멘드 읽어갈 거야 //입출력 관리 처리를 할 거면 예외처리 꼭 해줘야함
			if (cmd.equals("exit")) { // 입력글자가 exit라면
				System.out.println("종료합니다."); // 종료
				break;
			} else if (cmd.startsWith("new")) {
				processNewCommand(cmd.split(" ")); //공백을 기준으로 잘라. 배열로 리턴을 해주는 메소드. 잘라서 배열로 넣어준다 
			} else if (cmd.startsWith("change")) {
				processChangeCommand(cmd.split(" ")); // new hong@gmail.com 홍길동 1234 1234 이런식으로 입력해줘야햇 
			} else if (cmd.equals("list")) {
				//hong@gmail.com 홍길동 비밀번호 날짜 나오게 해보셈
				Map<String, Member> map =ctx.getBean("listSvc",MemberListService.class).selectList();
				for(String key : map.keySet()) {
					Member m = map.get(key);
					System.out.println(m.getId()+"\t"+m.getEmail()+"\t"+m.getName()+"\t"+m.getPassword()+"\t"+m.getRegisterDateTime());
					
				}
				
			} else if (cmd.startsWith("info")) {
				if (cmd.split(" ").length != 2) {
					return; 
				}
				Member member = ((MemberInfoService)ctx.getBean("infoSvc")).selectOne(cmd.split(" ")[1]);
				if(member==null) {
					System.out.println("등록되지 않은 이메일입니다.");
				} else {
					System.out.println("id:"+member.getId()+"email:"+member.getEmail()+"name:"+member.getName()+"date:"+member.getRegisterDateTime());
				}
			}
		}
	}


	// 메인메소드가 static이기 때문에 내부에서 호출하려면 다른 메소드든 필드든 다static이여야 쓸 수 있어서
	// 메인메소드 안에서 호출할 거라서 static 쓴 거야
	// ★등록메소드★
	private static void processNewCommand(String[] arg) {
		if (arg.length != 5) {
			return; // 중지되어버림 밑에 실행 X
		}
		
		MemberRegisterService regSvc = ctx.getBean("regSvc", MemberRegisterService.class); //강제형변환을 하나 이렇게 하나 똑같음
		
		//RegisterRequest : VO처럼 값을 담아둠
		RegisterRequest req = new RegisterRequest(); // 왜 생성해야해? 등록할 때 이 타입으로 매개변수를 받고 있기 때문에 이 타입 매개변수 대입 꼭 해줘야함 RegisterRequest : 값을 담아둠
		req.setEmail(arg[1]); // 0번째는 파일이름이라서 1로
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		// 입력한 4개의 값을 set set set set

		if (!req.isPasswordEqualToConfirmPassword()) { // 비밀번호랑 비밀번호확인이 같지 않은 경우
			System.out.println("비밀번호가 일치하지 않습니다.");
			return; // 메소드 중지.
		}
		try {
			regSvc.regist(req); // 비번 일치하면 등록
			System.out.println("회원등록완료");
		} catch (DuplicateMemberException e) {
			System.out.println("이메일 중복");
		}
	}

	// 이메일, 올드비번, 뉴비번 3개 필요
	// ★업데이트메소드★
	private static void processChangeCommand(String[] arg) {
		if (arg.length != 4) {// 필요한 건 3개인데 하나 뭐 필요할 거라서
			return; // 중지.

		}
		ChangePasswordService pwdSvc = ctx.getBean("pwdSvc", ChangePasswordService.class);
		try {
			pwdSvc.changePassword(arg[1], arg[2], arg[3]); // arg[0]은 다른 거 
			System.out.println("비밀번호 변경");
		} catch (MemberNotFoundException e) { // 이메일이 없는 거야
			System.out.println("회원이 존재하지 않습니다.");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 비밀번호가 일치하지 않습니다.");
		}
	}
	
	
}