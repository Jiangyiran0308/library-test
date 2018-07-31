package com.web.Filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String[] notFilter = new String[] { "/login"}; // 不过滤的uri
        String strUri = request.getRequestURI() ;
        System.out.println(strUri);
        request.getRequestDispatcher("/login").forward(request, response);


    }
}
