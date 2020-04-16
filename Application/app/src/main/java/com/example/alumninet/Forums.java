package com.example.alumninet;

import androidx.annotation.NonNull;

public class Forums {
    String title;
    String description;

    public Forums(){
    }

    public Forums(String title, String description){
        this.title = title;
        this.description = description;
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
