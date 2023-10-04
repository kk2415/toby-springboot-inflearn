package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

public class HellobootApplication {

	public static void main(String[] args) {
		/*
		 * Tomcat도 Java로 만들어진 프로그램이다.
		 * 자바로 만들었다는 의미는 클래스를 인스턴스화하여 객체의 메서드를 호출할 수 있다는 걸 의미한다.
		 * 스프링부트에는 인베디드 톰캣이라는 내장형 톰캣 라이브러리들을 제공한다.
		 * */

		/*
		 * 바로 Tomcat 클래스를 사용하여 톰캣을 구성하는 건 너무 복잡하고 번거롭다
		 * ex) Tomcat tomcat = new Tomcat();
		 *
		 * 스프링부트가 톰캣 서블릿 컨테이너를 내장해서 코드로 쉽게 시작할 수 있도록 만들어준 도우미 클래스가 있다.
		 * 바로 TomcatServletWebServerFactory 이다.
		 * */

		//부트가 톰캣 외에 제티나 언더토우 같은 다른 서블릿 컨테이너도 지원을 하기 위해 추상화를 해놨다.
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer();
		webServer.start();
	}

}
