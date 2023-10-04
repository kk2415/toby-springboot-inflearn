package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HellobootApplication {

	public static void main(String[] args) {
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext(); // GenericApplicationContext -> GenericWebApplicationContext
		applicationContext.registerBean(HelloController.class);
		applicationContext.registerBean(SimpleHelloService.class);
		applicationContext.refresh(); //컨테이너 초기화 및 (Bean)빈 생성

		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("dispatcherServlet",
					/**
					 * TODO:: url - 컨트롤러 매핑 정보가 필요하다, 스프링 초기에는 XML 설정파일로 연결하였다, 최근에는 컨트롤러에 직접 매핑 url을 입력하는 추세
					 * */
					new DispatcherServlet(applicationContext)
			).addMapping("/*");
		});
		webServer.start();
	}

}
