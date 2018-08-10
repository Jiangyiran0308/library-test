package com.web.Service;

import com.web.Model.Libbook;
import com.web.Model.Libuser;
import com.web.Model.UserAllBook;
import com.web.Model.UserBookRelation;
import com.web.Service.ServiceInterfaces.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleRelationService implements RelationService {

    @Autowired
    SimpleDataService simpleDataService ;
    @Autowired
    SimpleBookService simpleBookService ;

    public void add(HttpServletRequest relation){
        String userid = relation.getParameter("relationUserId") ;
        String isbn = relation.getParameter("relationIsbn") ;
        if (userid != null && isbn != null) {
            if((!userid.equals(""))&&(!isbn.equals(""))) {
                simpleDataService.addUserBookRelation(new UserBookRelation(userid, isbn));
            }
            else
                System.out.println("=====值不能为空!=====");
        }
    }

    public UserAllBook userAllBook(HttpServletRequest relation){
        String userid = relation.getParameter("searchRelationUserId") ;
        if(userid != null) {
            Libuser user = simpleDataService.searchUserById(userid);

            /*索引查询借书关系*/
            List<String> allbook = SimpleDataService.relationIndex.get(userid) ;
            if(allbook != null){
                List<Libbook> books = new ArrayList<>();
                for(String isbn : allbook) {
                    books.add(SimpleDataService.bookIndex.get(isbn) );
                }
                System.out.println("通过索引查询关系成功！*******");
                if(!books.isEmpty())
                    return (new UserAllBook(user,books));
            }



            List<String> allIsbn = simpleDataService.searchUserBookRelationById(userid);
            if(user != null && allIsbn != null) {
                List<Libbook> userAllBook = new ArrayList<Libbook>();
                for (String isbn : allIsbn) {
                    Libbook book = simpleDataService.searchBookByISBN(isbn) ;
                    if(book != null ) {
                        System.out.println("=====查询关系成功！=====");
                        userAllBook.add(book);
                    }
                }

                return (new UserAllBook(user,userAllBook));
            }else{
                if(user == null)
                    System.out.println("=====该用户不存在！=====");
                if(allIsbn == null)
                    System.out.println("=====该用户没有借书！=====");
            }
        }
        return null ;
    }

    public void delete(HttpServletRequest relation){
        if(relation != null) {
            String info = relation.getParameter("deleterelation");
            String userId = null;
            String isbn = null;
            if (info != null)
            {
                String[] str = info.split(";") ;
                if(str.length >= 2){
                    userId = str[0];
                    isbn = str[1] ;
                    if(!userId.equals("") && (!isbn.equals(""))){
                        simpleDataService.deleteUsetBookRelation(new UserBookRelation(userId,isbn));
                    }
                }

            }
        }
    }

    public void searchAll(){}


}
