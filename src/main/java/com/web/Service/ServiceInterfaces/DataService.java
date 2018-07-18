package com.web.Service.ServiceInterfaces;

import com.web.Model.* ;

import java.util.List;


public interface DataService {


    Libuser searchUserByUserName(String userName);

    Libuser searchUserById(String userId);

    Libbook searchBookByISBN(String bookIsbn);

    Libbook searchBookByName(String bookName);

    List<String> searchUserBookRelationById(String id);

    UserBookRelation searchUserBookRelationByISBN(String isbn);

    void addUser( Libuser user );
    void addBook( Libbook book );
    void addUserBookRelation( UserBookRelation relation );
    void deleteUser(String userId);
    void deleteBook(String bookIsbn);
    void deleteUsetBookRelation( UserBookRelation relation );
    void updataUser(Libuser user);
    void updateBook(Libbook book);
    void updataBookStatus(String isbn , short status);

}
