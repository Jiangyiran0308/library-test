package com.web.Service.ServiceInterfaces;

import com.web.Model.UserAllBook;

import javax.servlet.http.HttpServletRequest;

public interface RelationService {
    void add(HttpServletRequest relation);
    UserAllBook userAllBook(HttpServletRequest relation);
    void delete(HttpServletRequest relation);
}
