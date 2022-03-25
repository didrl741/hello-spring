package hello.hellospring.controller;

public class MemberForm
{
    // createMemberForm에서 입력한 name이 여기로 들어옴.
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
