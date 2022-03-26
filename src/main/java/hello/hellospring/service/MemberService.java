package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // MemberService는 MemberRepository가 필요. 이미 빈에 등록된 MemberRepository를 가져온다.
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    * 회원 가입
    * */
    public Long join(Member member) {

        // 동명이인 가입 불가. 4줄이나 되기때문에 따로 메서드로 뺐다. (컨트롤 알트 쉬프트 T)
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
