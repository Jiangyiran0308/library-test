package com.web.Model;

import java.util.List;

public class UserAllBook {

    private Libuser user;

    private List<Libbook> allbook;

    public UserAllBook(Libuser user , List<Libbook> allbook){
        this.user = user ;
        this.allbook = allbook ;
    }

    public Libuser getUser() {
        return user;
    }

    public List<Libbook> getAllbook() {
        return allbook;
    }

    public void setUser(Libuser user) {
        this.user = user;
    }

    public void setAllbook(List<Libbook> allbook) {
        this.allbook = allbook;
    }
}
