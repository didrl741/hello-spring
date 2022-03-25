package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 스프링 컨테이너라는 스프링 통을 생성해서 거기에 MemberController 객체를 생성해서 넣는다.(bean 객체로)
@Controller
public class MemberController {

    private final MemberService memberService;

    // MemberController는 MemberService가 필요. 이미 빈에 등록된 MemberService를 가져온다.
    @Autowired
    public MemberController(MemberService memberService) {      // DI
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    // 등록 누르면 MemberForm객체가 생성되면서 setter함수가 저절로 실행되며 name 전달.
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        System.out.println("member = " + member.getName());

        return "redirect:/";
    }

}
