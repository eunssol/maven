package chap05;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ExeAspect {
	
	@Pointcut("execution(public * chap05..*(..))") //퍼블릭이 적용된 chap05 패키지 안에서 모든 클래스의 모든 메소드...?를 지정
	private void publicTarget() {}
	
	@Around("publicTarget()") //실행될 때마다 밑에꺼 반복될 거임
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.nanoTime();
		System.out.println("메소드 실행전!!!");
		try {
			Object result = joinPoint.proceed(); //joinPoint라는 매개변수에 proceed라는 메소드가 있음 
			return result;
			
		} finally {
			System.out.println("메소드 실행끝!!!");
			long end = System.nanoTime();
			System.out.println("AOP: " + (end -start));
		}
	}
}
