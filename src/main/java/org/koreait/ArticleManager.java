package org.koreait;

public class ArticleManager {

    private int num;
    private String title;
    private String content;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ArticleManager(int num, String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleManager{" +
                "num=" + num +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
