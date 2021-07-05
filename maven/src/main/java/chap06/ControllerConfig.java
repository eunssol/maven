package chap06;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //설정파일이니까
public class ControllerConfig {
	
	@Bean //helloController이 이름으로 빈등록
	public HelloController helloController() {
		return new HelloController();
		
	}
}
