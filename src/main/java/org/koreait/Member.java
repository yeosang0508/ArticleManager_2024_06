package org.koreait;

public class Member {
    int id;
    String regDate;
    String loginId;
    String loginPw;
    String name;

    public Member(int id, String regDate, String loginId, String loginPw, String name) {
        this.id = id;
        this.regDate = regDate;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", regDate='" + regDate + '\'' +
                ", loginId='" + loginId + '\'' +
                ", loginPw='" + loginPw + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
