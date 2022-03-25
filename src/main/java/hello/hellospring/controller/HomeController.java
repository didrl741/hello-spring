package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// static의 index.html이 있지만, 이게 우선순위 더 높다.
// 관련 컨트롤러를 먼저 찾고, 없으면 그제서야 static을 찾음.
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
