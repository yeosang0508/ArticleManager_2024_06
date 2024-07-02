package org.koreait.mySolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int id = 1;

        List<ArticleManager> articleManagers = new ArrayList<>();

        while (true) {
            System.out.print("명령어) ");
            String order = scanner.nextLine();

            if (order.equals("article write")) {

                System.out.print("제목: ");
                String title = scanner.nextLine();
                System.out.print("내용: ");
                String body = scanner.nextLine();

                ArticleManager articleManager = new ArticleManager(id, title, body);

                articleManagers.add(articleManager);

                System.out.println(id++ + "번 글이 생성되었습니다.");

            } else if (order.equals("article list")) {
                System.out.println("번호   /   제목   /   내용");

                for (int i = articleManagers.size() - 1; i >= 0; i--) {

                    System.out.println("   " + articleManagers.get(i).getId() + "   /   " + articleManagers.get(i).getTitle() + "   /   " + articleManagers.get(i).getBody());
                }


            } else if (order.startsWith("article detail")){
//                String detail = order.split(" ", 2);

                for(ArticleManager articleManager : articleManagers){

//                    if(articleManager.getId() == articleManagers[])
                }

                if(articleManagers.size() == 0){

                }
            }
        }
    }
}

class ArticleManager {
    int id;
    String title;
    String body;

    public ArticleManager(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ArticleManager{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + body + '\'' +
                '}';
    }
}