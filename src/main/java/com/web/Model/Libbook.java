package com.web.Model;

/**
 * @Author: Jiang
 * @Date: Created in 15:50  2018\6\29 0029
 * @Description:
 * @Modified By:
 */
public class Libbook {
    private String isbn ;
    private String name ;
    private String author ;
    private long pages ;
    private short status ;

    public Libbook(String isbn,String name,String author,long pages){
        this.isbn = isbn ;
        this.name = name ;
        this.author = author ;
        this.pages =  pages ;
        this.status = 0 ;
    }
    public Libbook(String isbn,String name,String author,long pages,short status){
        this.isbn = isbn ;
        this.name = name ;
        this.author = author ;
        this.pages =  pages ;
        this.status = status ;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public short isStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }
}
