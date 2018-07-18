package com.web.Library.io;

import com.web.Model.Libbook;
import com.web.Model.Libuser;
import com.web.Model.UserBookRelation;

import java.util.List;

public interface WriteOrRead {

    void writeUserInfoToTXT(Libuser user) ;
    List<Libuser> readAllUserInfo();
    void writeBookInfoToTXT(Libbook book);
    List<Libbook> readAllBookInfo();
    void writeRelationInfoToTXT(UserBookRelation relation) ;
    List<UserBookRelation> readAllRelation() ;
    void deleteUserTXT();
    void deleteBookTXT();
    void deleteRelationTXT();
}
