package com.example.alumninet;

import androidx.annotation.NonNull;

import com.google.firebase.database.ServerValue;

import java.util.Date;

public class Forums {
    String title;
    String description;
    String forumKey;
    String username;
    String date ;
    Date currDate = new Date();



    public Forums(){
    }

    public Forums(String title, String description, String username){
        this.title = title;
        this.description = description;
        this.username = username;
        //this.userId = this.userId;
    }

    public String getForumKey() {
        return forumKey;
    }

    public void setForumKey(String forumKey) {
        this.forumKey = forumKey;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date) {
        //date = currDate.toString();
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NonNull
    @Override
    public String toString() {
        return title + " \n \t " + description;
    }

}
