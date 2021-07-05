package chap01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //설정파일은 위에 써줘야해 
public class AppContext {
	@Bean //하나의 Bean으로 동작할 메소드다 라고 표시 이 설정가지고 Bean을 get할 수 있는.......
	public Greeter greeter() {
		Greeter g = new Greeter();
		g.setFormat("%s, 안녕하세요");
		return g;
		
	} //<bean id=""어쩌구 저쩌구 한 거랑 똑같은 거래..............
}
