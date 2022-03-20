package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")        // 8080/hello 경로일때 밑의 함수를 실행시켜라
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // resources/templates의 hello.html에 가서 랜더링해라
    }

    // MVC 이용. html을 렌더링해서 뿌려준다. hello-mvc 경로로 가면 아래 함수를 실행. 그런데 파라미터를 꼭 넘겨야.
    // /hello-mvc?name=hi로 hi를 넘기면 hello-template으로 가서 name에 hi를 전달.
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model ) {
        model.addAttribute("name", name);
        return "hello-template";
    }


    // API 이용. 데이터를 바로 뿌려준다( 객체 : JSON  ,  문자열 : String )
    // 웹 페이지에서 소스보기 하면 html태그 하나도 없이 그냥 저 문자만 있다.
    @GetMapping("hello-string")
    @ResponseBody   //http의 바디에 이 데이타를 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name ) {
        return "API hello " + name;
    }


    // json을 넘기는 API방식.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
