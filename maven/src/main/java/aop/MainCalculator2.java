

package aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap05.Calculator;

public class MainCalculator2 {//스프링에 있는 AOP기능 이용해서 해보려고

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		Calculator cal = ctx.getBean("calculator", Calculator.class);
		System.out.println(cal.factorial(10));
		System.out.println(cal.getClass().getName());
	}

}