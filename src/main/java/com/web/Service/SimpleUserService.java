package com.web.Service;

import com.web.Library.io.WriteOrReadIO;
import com.web.Model.Libbook;
import com.web.Model.Libuser;
import com.web.Service.ServiceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jiang
 * @Date: Created in 11:23  2018\7\4 0004
 * @Description:
 * @Modified By:
 */
@Service
public class SimpleUserService implements UserService{

    @Autowired
    private SimpleDataService simpleDataService ;

    private WriteOrReadIO io = new WriteOrReadIO() ;

    public void add(HttpServletRequest userinfo){
        if(userinfo != null) {
            String id = userinfo.getParameter("userId");
            String name = userinfo.getParameter("userName");
            if ((id!=null)&&(name!=null)) {
                if((!id.equals(""))&&(!name.equals("")))
                    simpleDataService.addUser(new Libuser(id, name));
                else
                    System.out.println("=====值不能为空!=====");
            }
        }
    }

    public void update(HttpServletRequest userinfo){
        String name = userinfo.getParameter("newUserName");
        String id = userinfo.getParameter("newUserId");
        if(name != null && id != null){
            if(!name.equals("") && (!id.equals(""))){
                simpleDataService.updataUser(new Libuser(id,name));
            }
        }
    }

    public void delete(HttpServletRequest userinfo){
        String userid = userinfo.getParameter("deleteUserById");
        if(userid!=null){
            if(!userid.equals("")){
                simpleDataService.deleteUser(userid);
            }
        }
    }

    public List<Libuser> searchAll(){

        return simpleDataService.allUser;

    }

    public Libuser searchByIDorName(HttpServletRequest userinfo){
        String id = userinfo.getParameter("searchUserById");
        String name = userinfo.getParameter("searchUserByName") ;

        if(id != null ){
            Libuser user = simpleDataService.userIndex.get(id) ;
            if(user != null)
                return user ;
        }


        Libuser userById = simpleDataService.searchUserById(id) ;
        Libuser userByName = simpleDataService.searchUserByUserName(name) ;

        if(userById == null && userByName != null ) {
            System.out.println("查询用户成功！======");
            return userByName;
        }
        else if(userById != null && userByName == null ){
            System.out.println("查询用户成功！======");
            return userById ;
        }
        else if(userById!=null && userByName!=null &&userById.getId().equals(userByName.getId()) && userById.getName().equals(userByName.getName())) {
            System.out.println("查询用户成功！======");
            return userById;
        }
        return null ;
    }

}
