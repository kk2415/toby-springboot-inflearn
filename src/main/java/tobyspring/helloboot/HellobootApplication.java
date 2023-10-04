package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HellobootApplication {

	public static void main(String[] args) {
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {

			/**
			 * 스프링 초기화 중 (onRefresh()) 중에 디스패치 서블릿 설정
			 * */
			@Override
			protected void onRefresh() {
				super.onRefresh();

				ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet",
							new DispatcherServlet(this)
					).addMapping("/*");
				});
				webServer.start();
			}
		};

		applicationContext.registerBean(HelloController.class);
		applicationContext.registerBean(SimpleHelloService.class);

		/*
		 * 스프링 컨테이너 초기화 작업은 refresh() 메서드에서 다 일어난다.
		 * 메서드는 전형적인 템플릿 메서드 패턴을 사용했다.
		 *
		 * 템플릿 메서드 안에서 차례대로 호출되는 Hook 메서드 중 하나가 onRefresh() 메서드이다.
		 * 클래스를 상속받아 이 Hook 메서드를 재구현하면 된다.
		 * */
		applicationContext.refresh();
	}
}
