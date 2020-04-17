package com.example.alumninet;

import androidx.annotation.NonNull;

public class Forums {
    String title;
    String description;
    String forumKey;

    public Forums(){
    }

    public Forums(String title, String description){//, String forumKey){
        this.title = title;
        this.description = description;
        //this.forumKey= forumKey;
    }

    public String getPostKey() {
        return forumKey;
    }

    public void setPostKey(String postKey) {
        this.forumKey = postKey;
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

    @NonNull
    @Override
    public String toString() {
        return title + " \n \t " + description;
    }
}
