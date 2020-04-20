package com.example.alumninet;

public class Connection {

    private String fullname;
    private String username;
    private String email;
    private String interest1;
    private String interest2;
    private String interest3;
    private String bio;

    public Connection() {

    }

    public Connection(String fullname, String username, String email,
                      String interest1, String interest2, String interest3, String bio) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.interest1 = interest1;
        this.interest2 = interest2;
        this.interest3 = interest3;
        this.bio = bio;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInterest1() {
        return interest1;
    }

    public void setInterest1(String interest1) {
        this.interest1 = interest1;
    }

    public String getInterest2() {
        return interest2;
    }

    public void setInterest2(String interest2) {
        this.interest2 = interest2;
    }

    public String getInterest3() {
        return interest3;
    }

    public void setInterest3(String interest3) {
        this.interest3 = interest3;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


}