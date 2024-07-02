package org.koreait.Controller;

import org.koreait.ArticleManager;
import org.koreait.Container;

import java.util.ArrayList;
import java.util.List;

public class ArticleController {
    private static int num = 1;
    private static List<ArticleManager> articleManagers = new ArrayList<>();

    public ArticleController(){

    }

    public static void add() {

        System.out.print("제목: ");
        String title = Container.getScanner().nextLine();
        System.out.print("내용: ");
        String content = Container.getScanner().nextLine();
        System.out.println(num++ + "번 글이 생성되었습니다.");


        ArticleManager articleManager = new ArticleManager(num, title, content);
        articleManagers.add(articleManager);
    }

    public void list() {
        System.out.println("   번호   /   제목   /   내용   ");

        for (int i = articleManagers.size() - 1; i >= 0; i--) {
            System.out.println("   " + articleManagers.get(i).getNum() + "   /   " + articleManagers.get(i).getTitle() + "   /   " + articleManagers.get(i).getContent());

        }
    }

    public void detail() {

//        findnum()
    }

    public  ArticleManager findnum(int num) {

        for (ArticleManager articleManager : articleManagers) {
            if (articleManager.getNum() == num) {
                return articleManager;
            }

        }
        return null;
    }
}
