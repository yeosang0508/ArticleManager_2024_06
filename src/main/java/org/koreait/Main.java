package org.koreait;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Article> articles = new ArrayList<>();
    static List<Member> members = new ArrayList<>();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==프로그램 시작==");

        makeTestData();

        int lastArticleId = 3;
        int lastMemberId = 0;

        while (true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력하세요");
                continue;
            }
            if (cmd.equals("exit")) {
                break;
            }

            if (cmd.equals("member join")) {
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

            } else if (cmd.equals("member login")) {
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
                            System.out.println("나의 아이디 : " + member.getLoginId());
                            System.out.println("나의 이름 : " + member.getName());
                            System.out.println("Id : " + member.getId());
                            System.out.println("작성날짜 : " + member.getRegDate());
                            status = 1;
                            break;
                        }

                    }
                    continue;
                }


            } else if (cmd.equals("member logout")) {

            } else if (cmd.equals("article write")) {
                System.out.println("==게시글 작성==");
                int id = lastArticleId + 1;
                String regDate = Util.getNow();
                String updateDate = regDate;
                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String body = sc.nextLine();

                Article article = new Article(id, regDate, updateDate, title, body);
                articles.add(article);

                System.out.println(id + "번 글이 생성되었습니다");
                lastArticleId++;
            } else if (cmd.startsWith("article list")) {
                System.out.println("==게시글 목록==");
                if (articles.size() == 0) {
                    System.out.println("아무것도 없어");
                    continue;
                }

                String searchKeyword = cmd.substring("article list".length()).trim();

                List<Article> forPrintArticles = articles;

                if (searchKeyword.length() > 0) {
                    System.out.println("검색어 : " + searchKeyword);
                    forPrintArticles = new ArrayList<>();

                    for (Article article : articles) {
                        if (article.getTitle().contains(searchKeyword)) {
                            forPrintArticles.add(article);
                        }
                    }
                    if (forPrintArticles.size() == 0) {
                        System.out.println("  번호   /    날짜   /   제목   /   내용   ");
                        System.out.println("검색 결과 없음");
                        continue;
                    }
                }

                System.out.println("  번호   /    날짜   /   제목   /   내용   ");
                for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
                    Article article = forPrintArticles.get(i);
                    if (Util.getNow().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
                        System.out.printf("  %d   /   %s      /   %s   /   %s  \n", article.getId(), article.getRegDate().split(" ")[1], article.getTitle(), article.getBody());
                    } else {
                        System.out.printf("  %d   /   %s      /   %s   /   %s  \n", article.getId(), article.getRegDate().split(" ")[0], article.getTitle(), article.getBody());
                    }

                }

            } else if (cmd.startsWith("article detail")) {
                System.out.println("==게시글 상세보기==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticleById(id);

                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }
                System.out.println("번호 : " + foundArticle.getId());
                System.out.println("작성날짜 : " + foundArticle.getRegDate());
                System.out.println("수정날짜 : " + foundArticle.getUpdateDate());
                System.out.println("제목 : " + foundArticle.getTitle());
                System.out.println("내용 : " + foundArticle.getBody());
            } else if (cmd.startsWith("article delete")) {
                System.out.println("==게시글 삭제==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticleById(id);

                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }
                articles.remove(foundArticle);
                System.out.println(id + "번 게시글이 삭제되었습니다");
            } else if (cmd.startsWith("article modify")) {
                System.out.println("==게시글 수정==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticleById(id);

                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }
                System.out.println("기존 제목 : " + foundArticle.getTitle());
                System.out.println("기존 내용 : " + foundArticle.getBody());
                System.out.print("새 제목 : ");
                String newTitle = sc.nextLine();
                System.out.print("새 내용 : ");
                String newBody = sc.nextLine();

                foundArticle.setTitle(newTitle);
                foundArticle.setBody(newBody);
                foundArticle.setUpdateDate(Util.getNow());

                System.out.println(id + "번 게시글이 수정되었습니다");
            } else {
                System.out.println("사용할 수 없는 명령어입니다");
            }

        }
        System.out.println("==프로그램 종료==");
        sc.close();

    }

    private static boolean isJoinableLoginId(String loginId) {
        for (Member member : members) {
            if (member.getLoginId().equals(loginId)) {
                return false;
            }
        }
        return true;
    }

    private static Article getArticleById(int id) {
//        for (int i = 0; i < articles.size(); i++) {
//            Article article = articles.get(i);
//            if (article.getId() == id) {
//                return article;
//            }
//        }
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    private static void makeTestData() {
        System.out.println("테스트 데이터 생성");
        articles.add(new Article(1, "2023-12-12 12:12:12", "2023-12-12 12:12:12", "제목123", "내용1"));
        articles.add(new Article(2, Util.getNow(), Util.getNow(), "제목72", "내용2"));
        articles.add(new Article(3, Util.getNow(), Util.getNow(), "제목1233", "내용3"));

        System.out.println("회원 테스트 데이터 생성");
        members.add(new Member(1, "2024-02-25 12:12:12", "A", "A", "A"));

        members.add(new Member(2, Util.getNow(), "B", "B", "B"));

        members.add(new Member(3, Util.getNow(), "C", "C", "C"));


    }
}