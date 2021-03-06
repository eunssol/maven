package chap09;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //얘도 설정파일이라서 설정파일 사용하겠단 표시 
@ComponentScan(basePackages = {"chap09"})
@EnableWebMvc //스프링에 있는 mvc를 사용하겠다! 라는 설정
@EnableTransactionManagement //트랜잭션 사용할 거임
public class MvcConfig implements WebMvcConfigurer{
	
	@Value("${db.driver}")
	private String driver;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String username;
	@Value("${db.password}")
	private String password;
	
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer conf) {
		conf.enable();
		
	}
	@Override
	public void configureViewResolvers(ViewResolverRegistry reg) {
		//(jsp가 있는 경로[시작경로],확장자)
		reg.jsp("/WEB-INF/view/",".jsp");
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry reg) {
		//아무런 로직이 없으니까 여기다 추가하자
		//뷰컨트롤러를 추가하는 메소드
		//비즈니스로직이 필요없는(디자인만 있는 페이지) url과 jsp 매핑
		reg.addViewController("/index.do").setViewName("index");  //index.do라고 접속하면 뜬다
	}
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		return dataSource;
		//셋된 dataSource를 빈에 저장 
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception { 
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		ssfb.setMapperLocations(resolver.getResources("classpath://mapper/**/*.xml"));
		return ssfb.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sqlSessionTemplate;
	}
	
	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
		
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry reg) {
		reg.addInterceptor(loginInterceptor()).addPathPatterns("/member/mypage.do").addPathPatterns("").addPathPatterns("");
		//excludePathPatterns()
		//관리자 : .addPathPatterns("/admin/**").excludePathPatterns("/admin/index.do")
		
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer property() {
		PropertySourcesPlaceholderConfigurer conf = new PropertySourcesPlaceholderConfigurer();
		conf.setLocations(new ClassPathResource("db.properties")); //주입?
		return conf; //빈으로 등록 완료
	}
	
	@Bean 
	public PlatformTransactionManager txmanager() {
		DataSourceTransactionManager ptm = new DataSourceTransactionManager();
		ptm.setDataSource(dataSource()); //주입된 객체가 여기에 또 주입이 되고
		return ptm; //주입된 상태의 객체가 리턴이 된다.
		
		
	}
}


