package chap01;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main2 { //xml에서 가져와

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext ("chap01/beans.xml");
		Greeter g = ctx.getBean("greeter", Greeter.class); //얘도 가능
		System.out.println(g.greet("홍길동2하이루"));
		
	}

}
