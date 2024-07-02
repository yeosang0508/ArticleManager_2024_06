package org.koreait;

import org.koreait.Controller.ArticleController;

import java.util.ArrayList;
import java.util.List;


public class App {

    public static void run() {

        ArticleController articleController = new ArticleController();

        int num = 1;

        while (true) {
            System.out.print("명령어) ");
            String order = Container.getScanner().nextLine();

            switch (order) {
                case "article write":
                    articleController.add();
                    break;

                case"article list":
                    articleController.list();
                    break;

                case "detail":
                    articleController.detail();
                    break;
            }


        }
    }
}