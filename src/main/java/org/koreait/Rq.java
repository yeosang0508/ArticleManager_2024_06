package org.koreait;

import java.util.List;

public class Rq {
    String cmd;
    static List<Article> articles;

    public Rq(String cmd, List<Article> articles) {
        this.cmd = cmd;
        this.articles = articles;
    }

    public static Article request(String cmd) {


        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = null;

        for (Article article : articles) {
            if (article.getId() == id) {
                foundArticle = article;
                return foundArticle;
            }
        }

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");

        }
        return null;
    }
}
