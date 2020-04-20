package com.example.alumninet;

public class Comment {
    private String content;
    private String username;
    private String date;


    public Comment() {
    }

    public Comment(String content, String username, String date) {
        this.content = content;
        this.username = username;
        this.date = date;
    }

    public String getComment() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String uid) {
        this.username = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Object timestamp) {
        this.date = date;
    }
}
