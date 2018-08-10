package com.web.Service;

import com.web.Library.io.WriteOrReadIO;
import com.web.Model.Libbook;
import com.web.Model.Libuser;
import com.web.Service.ServiceInterfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: Jiang
 * @Date: Created in 17:38  2018\7\5 0005
 * @Description:
 * @Modified By:
 */
@Service
public class SimpleBookService implements BookService{

    @Autowired
    private SimpleDataService simpleDataService ;


    public void add(HttpServletRequest bookinfo){
        if(bookinfo != null ){
            String isbn = bookinfo.getParameter("bookIsbn");
            String name = bookinfo.getParameter("bookName");
            String author = bookinfo.getParameter("bookAuthor");
            String page = bookinfo.getParameter("bookPages");
            //long pages = 0 ;
            if(isbn != null && name != null && author != null & page != null ){
                if((!isbn.equals(""))&&(!name.equals(""))&&(!author.equals(""))&&(!page.equals("")))
                    simpleDataService.addBook(new Libbook(isbn , name , author , Long.valueOf(page) ));
                else
                    System.out.println("=====值不能为空!=====");
            }
        }
    }

    public void update(HttpServletRequest bookinfo){
        if(bookinfo != null ){
            String isbn = bookinfo.getParameter("newBookIsbn") ;
            String name = bookinfo.getParameter("newBookName");
            String author = bookinfo.getParameter("newBookAuthor");
            String page = bookinfo.getParameter("newBookPage");
            //String status = bookinfo.getParameter("updateStatus") ;
            if(isbn != null && name != null && author != null & page != null ){
                if((!isbn.equals(""))&&(!name.equals(""))&&(!author.equals(""))&&(!page.equals("")))
                    simpleDataService.updateBook(new Libbook(isbn , name , author , Long.valueOf(page) ));
                else
                    System.out.println("=====值不能为空!=====");
            }
        }
    }

    public void delete(HttpServletRequest bookinfo){
        if(bookinfo != null ){
            String isbn = bookinfo.getParameter("deleteBookByIsbn") ;
            if(isbn != null ){
                if(!isbn.equals("")) {
                    simpleDataService.deleteBook(isbn);
                }
                //System.out.println(isbn);
            }
        }
    }

    public List<Libbook> searchAll(){
        return simpleDataService.allBook ;
    }

    public Libbook searchByIDorName(HttpServletRequest userinfo){
        String isbn = userinfo.getParameter("searchUserByIsbn") ;
        String name = userinfo.getParameter("searchUserByName") ;

        if(isbn != null){
            Libbook book = SimpleDataService.bookIndex.get(isbn) ;
            if(book != null) {
                System.out.println("通过索引查询成功！*******");
                return book;
            }
        }

        Libbook bookByIsbn = simpleDataService.searchBookByISBN(isbn) ;
        Libbook bookByName = simpleDataService.searchBookByName(name) ;
        if(bookByIsbn == null && bookByName != null ) {
            System.out.println("查询书籍成功！======");
            return bookByName;
        }
        else if(bookByIsbn != null && bookByName == null ){
            System.out.println("查询书籍成功！======");
            return bookByIsbn ;
        }
        else if(bookByIsbn!=null && bookByName!=null &&bookByIsbn.getIsbn().equals(bookByName.getIsbn()) && bookByIsbn.getName().equals(bookByName.getName())) {
            System.out.println("查询书籍成功！======");
            return bookByIsbn;
        }
        return null ;
    }

}
