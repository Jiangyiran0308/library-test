package com.web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListenerSevice implements ServletContextListener{


    private SimpleDataService simpleDataService = new SimpleDataService();

    @Override
    public void contextDestroyed(ServletContextEvent context) {

    }

    @Override
    public void contextInitialized(ServletContextEvent context) {
        // 上下文初始化执行
        System.out.println("================>[ServletContextListener]自动加载启动开始.");
        simpleDataService.LibuserIndex();
        simpleDataService.LibbooksIndex();

    }

}
