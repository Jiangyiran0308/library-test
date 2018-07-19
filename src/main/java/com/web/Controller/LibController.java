package com.web.Controller;

import com.web.Model.Libuser;
import com.web.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Author: Jiang
 * @Date: Created in 16:38  2018\6\28 0028
 * @Description:
 * @Modified By:
 */


@Controller
@RequestMapping(value = "/",method = RequestMethod.GET)
public class LibController {
    @Autowired
    private SimpleUserService simpleUserService ;
    @Autowired
    private SimpleBookService simpleBookService ;
    @Autowired
    private SimpleRelationService simpleRelationService ;

    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request ,HttpServletResponse response){
        logger.info("Welcome!!!");
        return "index";
    }

    @RequestMapping(value = "/Library", method = RequestMethod.GET)
    public String printHello(HttpServletRequest request ,HttpServletResponse response) {
        simpleUserService.add(request);
        simpleBookService.add(request);
        simpleRelationService.add(request);
        return "home";
    }

    @RequestMapping(value = "/AllUser", method = RequestMethod.GET)
    public String searchAllUser(HttpServletRequest request,HttpServletResponse response){
        simpleUserService.delete(request);
        simpleUserService.update(request);
        request.setAttribute("searchuser" ,simpleUserService.searchByIDorName(request));
        request.setAttribute("alluser" , simpleUserService.searchAll());
        return "alluser";
    }

    @RequestMapping(value = "/AllBook", method = RequestMethod.GET)
    public String searchAllBook(HttpServletRequest request,HttpServletResponse response){
        simpleBookService.delete(request);
        simpleBookService.update(request);
        request.setAttribute("searchbook" ,simpleBookService.searchByIDorName(request));
        request.setAttribute("allbook" , simpleBookService.searchAll());
        return "allbook";
    }

    @RequestMapping(value = "/AllRelation", method = RequestMethod.GET)
    public String searchAllRelation(HttpServletRequest request,HttpServletResponse response){
        simpleRelationService.delete(request);
        request.setAttribute("searchrelation" ,simpleRelationService.userAllBook(request));
        //req.setAttribute("allbook" , simpleBookService.searchAll());
        return "allrelation";
    }


}
