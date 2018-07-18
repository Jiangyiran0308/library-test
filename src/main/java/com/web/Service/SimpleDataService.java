package com.web.Service;

import com.web.Library.io.WriteOrReadIO;
import com.web.Model.Libbook;
import com.web.Model.Libuser;
import com.web.Model.UserBookRelation;
import com.web.Service.ServiceInterfaces.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
 public class SimpleDataService implements DataService {

 private WriteOrReadIO io = new WriteOrReadIO() ;

 public List<Libuser> allUser = io.readAllUserInfo();

 public List<Libbook> allBook = io.readAllBookInfo() ;

 public List<UserBookRelation> allRelation = io.readAllRelation();



 public Libuser searchUserByUserName(String userName){
  if(allUser!=null){
   for(Libuser user : allUser){
    if(user.getName().equals(userName))
     return user ;
   }
  }
  return null ;

 }

 public Libuser searchUserById(String userId){
  if(allUser!=null){
   for(Libuser user : allUser){
    if(user.getId().equals(userId))
     return user ;
   }
  }
  return null ;

 }

 public Libbook searchBookByISBN(String bookIsbn){
  if(allBook!=null){
   for(Libbook book : allBook){
    if(book.getIsbn().equals(bookIsbn))
     return book ;
   }
  }
  return null ;

 }

 public Libbook searchBookByName(String bookName){
  if(allBook!=null){
   for(Libbook book : allBook){
    if(book.getName().equals(bookName))
     return book ;
   }
  }
  return null ;

 }

 public List<String> searchUserBookRelationById(String id){
  List<String> userRelation = new ArrayList<String>();
  if(allRelation!= null){
   for(UserBookRelation relation : allRelation){
    if(relation.getUserid().equals(id)){
     userRelation.add(relation.getBookisbn());
    }
   }
  }
  return userRelation ;
 }

 public UserBookRelation searchUserBookRelationByISBN(String isbn){
  if(allRelation!= null){
   for(UserBookRelation relation : allRelation){
    if(relation.getBookisbn().equals(isbn)){
     return relation ;
    }
   }
  }
  return null ;
 }


 public void addUser( Libuser user ){
  Libuser libuser = searchUserById(user.getId());
  if(libuser == null){
   allUser.add(user);
   io.writeUserInfoToTXT(user);
   System.out.println("添加用户成功！=====用户名"+ user.getName() + "=====ID" + user.getId());
  }
  else{
   System.out.println("添加用户失败！");
  }
 }

 public void addBook( Libbook book ){
  Libbook libbook = searchBookByISBN(book.getIsbn()) ;
  if(libbook == null){
   allBook.add(book);
   io.writeBookInfoToTXT(book);
   System.out.println("=====添加书籍成功！======");
  }
  else
   System.out.println("=====添加书籍失败！=====");
 }

 public void addUserBookRelation( UserBookRelation relation ){
  if(relation != null) {
   Libuser user = searchUserById(relation.getUserid()) ;
   Libbook book = searchBookByISBN(relation.getBookisbn()) ;
   if( user != null && book != null ) {
    if(searchUserBookRelationById(relation.getUserid()).size() <=5 ) {
     io.writeRelationInfoToTXT(relation);
     allRelation.add(relation);
     updataBookStatus(book.getIsbn(), (short) 1);
    }else{
     System.out.println("=====该用户借书已经达到五本！=====");
    }
   }else{
    if(user == null )
     System.out.println("=====用户不存在！=====");
    if(book == null )
     System.out.println("=====书籍不存在！=====");
   }
  }

 }

 public void deleteUser(String userId){
  Libuser user = searchUserById(userId) ;
  if(user != null){
   allUser.remove(user) ;
   System.out.println("=====删除用户成功！=====");
   io.deleteUserTXT();
   for(Libuser libuser : allUser){
    io.writeUserInfoToTXT(libuser);
   }
   List<String> userbook = searchUserBookRelationById(userId) ;
   if(userbook != null) {
    for (String bookisbn : userbook) {
     deleteUsetBookRelation(new UserBookRelation(userId, bookisbn));
    }
   }
  }
 }

 public void deleteBook(String bookIsbn){
  Libbook book = searchBookByISBN(bookIsbn) ;
  if(book != null){
   allBook.remove(book) ;
   System.out.println("=====删除书籍成功！=====");
   io.deleteBookTXT();
   for(Libbook libbook : allBook ){
    io.writeBookInfoToTXT(libbook);
   }
   UserBookRelation relation = searchUserBookRelationByISBN(book.getIsbn());
   if(relation != null)
    deleteUsetBookRelation(relation);
  }
 }

 public void deleteUsetBookRelation(UserBookRelation relation){
  if(relation != null) {
   UserBookRelation re = searchUserBookRelationByISBN(relation.getBookisbn());
   if (re != null) {
    allRelation.remove(re);
    System.out.println("=====删除关系成功！=====");
    io.deleteRelationTXT();
    for (UserBookRelation rec : allRelation) {
     io.writeRelationInfoToTXT(rec);
    }
   }
  }
 }

 public void updataUser(Libuser user){
  if(user != null){
   Libuser olduser = searchUserById(user.getId()) ;
   if(olduser != null ){
    deleteUser(olduser.getId());
    olduser.setName(user.getName());
    addUser(olduser);
    System.out.println("=====更新用户信息成功！=====");
   }
  }

 }

 public void updateBook(Libbook book){
  if(book != null){
   Libbook oldbook = searchBookByISBN(book.getIsbn()) ;
   if(oldbook != null ){
    deleteBook(oldbook.getIsbn());
    oldbook.setName(book.getName());
    oldbook.setAuthor(book.getAuthor());
    oldbook.setPages(book.getPages());
    addBook(oldbook);
   }
  }

 }

 public void updataBookStatus(String isbn , short status ){
  if(isbn != null ){
   Libbook book = searchBookByISBN(isbn) ;
   if(book != null){
    book.setStatus(status);
    updateBook(book);
   }
  }

 }


}
