package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/*
* 디스패처 서블릿이 매핑 정보를 만들 때 기본적으로 클래스 레벨에 있는 정보를 먼저 참고한다.
* 그 뒤 메서드 레벨에 붙어있는 애노테이션 매핑 정볼르 참고한다.
* */
@RequestMapping
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    //@RequestMapping(value = "/hello", method = RequestMethod.GET) <=== 예전 방식
    @ResponseBody
    @GetMapping("/hello")
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
