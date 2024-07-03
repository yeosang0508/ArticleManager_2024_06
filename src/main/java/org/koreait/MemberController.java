package org.koreait;

import java.util.*;

public class MemberController extends Controller {

    private Scanner sc;
    private List<Member> members;
    private Map<String, Member> memberMap;
    private String cmd;

    private int lastMemberId = 3;

    public MemberController(Scanner sc) {
        this.sc = sc;
        members = new ArrayList<>();
        memberMap = new HashMap<>();
    }

    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "join":
                doJoin();
                break;
            case "login":
                dologin();
                break;
            case "logout":
                break;
            default:
                System.out.println("명령어 확인 (actionMethodName) 오류");
                break;
        }
    }


    private void doJoin() {
        System.out.println("==회원가입==");
        int id = lastMemberId + 1;
        String regDate = Util.getNow();
        String loginId = null;
        while (true) {
            System.out.print("로그인 아이디 : ");
            loginId = sc.nextLine().trim();
            if (isJoinableLoginId(loginId) == false) {
                System.out.println("이미 사용중이야");
                continue;
            }
            break;
        }
        String loginPw = null;
        while (true) {
            System.out.print("비밀번호 : ");
            loginPw = sc.nextLine();
            System.out.print("비밀번호 확인 : ");
            String loginPwConfirm = sc.nextLine();

            if (loginPw.equals(loginPwConfirm) == false) {
                System.out.println("비번 다시 확인해");
                continue;
            }
            break;
        }

        System.out.print("이름 : ");
        String name = sc.nextLine();

        Member member = new Member(id, regDate, loginId, loginPw, name);
        members.add(member);

        System.out.println(id + "번 회원이 가입되었습니다");
        lastMemberId++;
    }

    private void dologin() {
        byte status = 0;

        while (status == 0) {
            System.out.print("아이디 : ");
            String loginId = sc.nextLine().trim();

            System.out.print("비밀번호 : ");
            String loginPw = sc.nextLine().trim();


            for (Member member : members) {
                if (!member.getLoginId().equals(loginId) || !member.getLoginPw().equals(loginPw)) {
                    System.out.println("로그인 실패, 아이디 또는 비밀번호 확인해주세요");
                    break;

                } else {
                    memberMap.put(member.getLoginId(), member);

                    System.out.println("로그인 완료");
                    System.out.println("나의 아이디 : " + member.getLoginId());
                    System.out.println("나의 이름 : " + member.getName());
//                    System.out.println("Id : " + member.getId());
//                    System.out.println("작성날짜 : " + member.getRegDate());
                    status = 1;
                    break;
                }

            }
            continue;
        }
    }

    private void dologout() {
        if (memberMap.size() == 0) {
            System.out.println("로그인 상태가 아닙니다.");
            return;
        }

        System.out.println("로그아웃 완료");
        memberMap.clear();
    }


    private boolean isJoinableLoginId(String loginId) {
        for (Member member : members) {
            if (member.getLoginId().equals(loginId)) {
                return false;
            }
        }
        return true;
    }

    public void makeTestData() {
        System.out.println("회원 테스트 데이터 생성");
        members.add(new Member(1, Util.getNow(), "test1", "test1", "test1"));
        members.add(new Member(2, Util.getNow(), "test2", "test2", "test2"));
        members.add(new Member(3, Util.getNow(), "test3", "test3", "test3"));
    }
}