package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 스프링 컨테이너라는 스프링 통을 생성해서 거기에 MemberController 객체를 생성해서 넣는다.(bean 객체로)
@Controller
public class MemberController {

    private final MemberService memberService;

    // MemberController는 MemberService가 필요. 이미 빈에 등록된 MemberService를 가져온다.
    @Autowired
    public MemberController(MemberService memberService) {      // DI
        this.memberService = memberService;
    }
}
