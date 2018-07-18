package com.web.Model;

public class UserBookRelation {
    private String userid ;
    private String bookisbn ;

    public UserBookRelation(String id , String isbn){
        this.userid = id ;
        this.bookisbn = isbn ;
    }

    public String getBookisbn() {
        return bookisbn;
    }

    public void setBookisbn(String bookisbn) {
        this.bookisbn = bookisbn;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
