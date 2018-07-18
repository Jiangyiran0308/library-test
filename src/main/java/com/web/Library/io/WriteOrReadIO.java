package com.web.Library.io;

import com.web.Model.Libbook;
import com.web.Model.Libuser;
import com.web.Model.UserBookRelation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: Jiang
 * @Date: Created in 14:33  2018\7\4 0004
 * @Description:
 * @Modified By:
 */
public class WriteOrReadIO implements WriteOrRead{
    private String UserFileName = "F:\\My Document\\Java_study\\UserInfo.txt" ;
    private String BookFileName = "F:\\My Document\\Java_study\\BookInfo.txt" ;
    private String RelationFileName = "F:\\My Document\\Java_study\\UserBookRelation.txt" ;

    public void writeUserInfoToTXT(Libuser user){
        String info = user.getId() + "|" + user.getName() ;
        FileWriter fw = null ;
        try {
            File f=new File(UserFileName);
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(info);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Libuser> readAllUserInfo(){
        List<String> alluser = new ArrayList<String>();
        List<Libuser> userInfo = new ArrayList<Libuser>();
        File f = new File(UserFileName);
        if( !f.exists() )
            return null ;
        try{
            BufferedReader br = new BufferedReader(new FileReader(f));//
            String s = null;
            while((s = br.readLine())!=null){
               alluser.add(s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        for(int i = 0 ; i < alluser.size() ; i++ ){
            if(alluser.get(i) != null ){
                String[] str = alluser.get(i).split("\\|") ;
                userInfo.add(new Libuser(str[0],str[1]));
            }
        }
        return userInfo ;
    }

    public void writeBookInfoToTXT(Libbook book){
        String info = book.getIsbn()+"|"+book.getName()+"|"+book.getAuthor()+"|"+String.valueOf(book.getPages()+"|"+String.valueOf(book.isStatus()));
        FileWriter fw = null ;
        try {
            File f=new File(BookFileName);
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(info);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Libbook> readAllBookInfo(){
        List<String> alluser = new ArrayList<String>();
        List<Libbook> bookInfo = new ArrayList<Libbook>();
        File f = new File(BookFileName);
        if( !f.exists() )
            return null ;
        try{
            BufferedReader br = new BufferedReader(new FileReader(f));//
            String s = null;
            while((s = br.readLine())!=null){
                alluser.add(s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        for(int i = 0 ; i < alluser.size() ; i++ ){
            if(alluser.get(i) != null ){
                String[] str = alluser.get(i).split("\\|") ;
                if(str.length >=5 ) {
                    bookInfo.add(new Libbook(str[0], str[1], str[2], Long.valueOf(str[3]),Short.valueOf(str[4])));
                }
            }
        }
        return bookInfo ;
    }

    public void writeRelationInfoToTXT(UserBookRelation relation){
        String info = relation.getUserid() + "|" + relation.getBookisbn() ;
        FileWriter fw = null ;
        try {
            File f=new File(RelationFileName);
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(info);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<UserBookRelation> readAllRelation(){
        List<String> alluser = new ArrayList<String>();
        List<UserBookRelation> allrelation = new ArrayList<UserBookRelation>();
        File f = new File(RelationFileName);
        if( !f.exists() )
            return null ;
        try{
            BufferedReader br = new BufferedReader(new FileReader(f));//
            String s = null;
            while((s = br.readLine())!=null){
                alluser.add(s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        for(int i = 0 ; i < alluser.size() ; i++ ){
            if(alluser.get(i) != null ){
                String[] str = alluser.get(i).split("\\|") ;
                allrelation.add(new UserBookRelation(str[0],str[1]));
            }
        }
        return allrelation ;
    }

    public void deleteUserTXT(){
        File file = new File(UserFileName) ;
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除文件" + UserFileName + "成功！");
            } else {
                System.out.println("删除文件" + UserFileName + "失败！");
            }
        } else {
            System.out.println("删除失败：" + UserFileName + "不存在！");
        }
    }

    public void deleteBookTXT(){
        File file = new File(BookFileName) ;
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除文件" + BookFileName + "成功！");
            } else {
                System.out.println("删除文件" + BookFileName + "失败！");
            }
        } else {
            System.out.println("删除失败：" + BookFileName + "不存在！");
        }
    }

    public void deleteRelationTXT(){
        File file = new File(RelationFileName) ;
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除文件" + RelationFileName + "成功！");
            } else {
                System.out.println("删除文件" + RelationFileName + "失败！");
            }
        } else {
            System.out.println("删除失败：" + RelationFileName + "不存在！");
        }
    }

}
