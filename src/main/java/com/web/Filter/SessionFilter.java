package com.web.Filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: Jiang
 * @Date: Created in 16:49  2018\7\31 0031
 * @Description:
 * @Modified By:
 */
public class SessionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("====测试Filter功能====拦截用户登陆====");
        String strUri = request.getRequestURI() ;

        if(!strUri.equals("/login")) {
            HttpSession session = request.getSession();
            System.out.println(session.getId());
            Map account = (Map) session.getAttribute("account");
            if (account == null) {
                System.out.println(String.valueOf(account));
                response.sendRedirect("/login");
            } else {
                System.out.println(strUri);
                filterChain.doFilter(request, response);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
