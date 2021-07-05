package chap01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class); //greeter라는 이름으로 빈 등록 (메소드 이름)
		//메소드 이름이 빈 이름으로 자동등록 됩니다!
		
		Greeter g = (Greeter)ctx.getBean("greeter"); //가져옴 //그리터라는 이름으로 가져옴
		System.out.println(g.greet("홍길동1"));
//		Greeter g = ctx.getBean("greeter", Greeter.class); //얘도 가능
//		System.out.println(g.greet("홍길동"));
		
	}

}
