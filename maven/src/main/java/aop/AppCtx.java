
package aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import chap05.Calculator;
import chap05.ExeAspect;
import chap05.ImplCalculator;
import chap05.ImplCalculator2;

@Configuration //스프링의 설정파일 표시
@EnableAspectJAutoProxy //스프링 AOP 적용됨 이거 써야만@!
public class AppCtx {
	@Bean
	public ExeAspect exeAspect() {
		return new ExeAspect();
	}
	@Bean
	public Calculator calculator() {
		return new ImplCalculator();
	}
	@Bean
	public Calculator calculator2() {
		return new ImplCalculator2();
	}
}