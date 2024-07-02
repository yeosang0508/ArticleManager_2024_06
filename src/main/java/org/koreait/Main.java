package org.koreait;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Article> articles = new ArrayList<>();
    static List<Member> signUp = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==프로그램 시작==");

        makeTestData();

        int lastArticleId = 3;

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

            if (cmd.equals("article write")) {
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
                } else if (!cmd.equals("article list")) {
                    String list = cmd.split(" ")[2];
                    System.out.println("  번호   /    날짜   /   제목   /   내용   ");
                    for (int i = articles.size() - 1; i >= 0; i--) {
                        if (articles.get(i).getTitle().contains(list)) {
                            Article article = articles.get(i);
                            Util.when(article);
                        }
                    }

                } else {
                    System.out.println("  번호   /    날짜   /   제목   /   내용   ");
                    for (int i = articles.size() - 1; i >= 0; i--) {
                        Article article = articles.get(i);
                        Util.when(article);
                    }


                }
            } else if (cmd.startsWith("article detail")) {
                System.out.println("==게시글 상세보기==");
                Rq.request(cmd);

                System.out.println("번호 : " + Rq.request(cmd).getId());
                System.out.println("작성날짜 : " + Rq.request(cmd).getRegDate());
                System.out.println("수정날짜 : " + Rq.request(cmd).getUpdateDate());
                System.out.println("제목 : " + Rq.request(cmd).getTitle());
                System.out.println("내용 : " + Rq.request(cmd).getBody());

            } else if (cmd.startsWith("article delete")) {
                System.out.println("==게시글 삭제==");
                articles.remove(Rq.request(cmd));

                System.out.println("해당 게시글이 삭제되었습니다");
            } else if (cmd.equals("article signup")) {

                System.out.print("loginId: ");
                String loginId = sc.nextLine();

                System.out.print("loginPw: ");
                String loginPw = sc.nextLine();

                System.out.print("name: ");
                String name = sc.nextLine();

                System.out.print("Id: ");
                int Id = sc.nextInt();
                String regDate = "";
                for (int i = 0; i < articles.size(); i++) {
                    if (Id == articles.get(i).getId()) {
                        Id = articles.get(i).getId();
                        regDate = articles.get(i).getRegDate();
                    }
                }
                Member member = new Member(Id, regDate, loginId, loginPw, name);

                signUp.add(member);

                System.out.println("회원가입 완료");


            } else if (cmd.startsWith("article modify")) {
                System.out.println("==게시글 수정==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = null;

                for (Article article : articles) {
                    if (article.getId() == id) {
                        foundArticle = article;
                        break;
                    }
                }

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

    private static void makeTestData() {
        System.out.println("테스트 데이터 생성");
        articles.add(new Article(1, "2023-12-12 12:12:12", "2023-12-12 12:12:12", "제목1", "내용1"));
        articles.add(new Article(2, Util.getNow(), Util.getNow(), "제목2", "내용2"));
        articles.add(new Article(3, Util.getNow(), Util.getNow(), "제목3", "내용3"));
    }
}

