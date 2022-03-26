package hello.hellospring.domain;

import javax.persistence.*;

// 이제 Member는 jpa가 관리함.
@Entity
public class Member {

    // Primary Key. db가 id 자동으로 만들어주는것 = identity
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
