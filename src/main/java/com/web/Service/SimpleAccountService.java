package com.web.Service;

import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jiang
 * @Date: Created in 11:23  2018\8\1 0001
 * @Description:
 * @Modified By:
 */
@Service
public class SimpleAccountService {
    public void selectAccount(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("admin") ;
        String pass = request.getParameter("password") ;
        Map m = new HashMap() ;
        String accountName = "admin" ;
        String accoutPass = "123456" ;
        if(name != null && pass != null) {
            if (name.equals(accountName))
                if (pass.equals(accoutPass)) {
                    m.put(accountName, accoutPass);
                    if(m != null) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("account", m);
                        String sessionId = session.getId();
                        Cookie cookie = new Cookie("JSESSIONID",sessionId);
                        cookie.setMaxAge(60 * 30);
                        cookie.setPath(request.getContextPath());
                        response.addCookie(cookie);

                        if (session.isNew()) {
                            System.out.println("session创建成功，session的id是：" + sessionId);
                            request.getRequestDispatcher("/").forward(request, response);
                        } else {
                            System.out.println("服务器已经存在该session了，session的id是：" + sessionId);
                            request.getRequestDispatcher("/").forward(request, response);
                        }
                    }
                }
                else {
                    System.out.println("密码错误！");
                   // request.setAttribute("passError",1);
                    return;
                }
            else {
                System.out.println("用户不存在!");
              //  request.setAttribute("accountError",1);
                return;
            }

        }
    }
    public void quitAccount(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
        if(request != null){
            String logout = request.getParameter("logout") ;
            if(logout != null ) {
                if(logout.equals("1")) {
                    HttpSession session = request.getSession(false);
                    session.removeAttribute("account");
                    System.out.println("******退出");
                }
            }
        }
    }


}

