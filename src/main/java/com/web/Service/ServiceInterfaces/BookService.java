package com.web.Service.ServiceInterfaces;

import com.web.Model.* ;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: Jiang
 * @Date: Created in 16:53  2018\6\29 0029
 * @Description:
 * @Modified By:
 */

public interface BookService {

    void add(HttpServletRequest bookinfo);

    void update(HttpServletRequest bookinfol);

    void delete(HttpServletRequest bookinfo);

    List<Libbook> searchAll();

    Libbook searchByIDorName(HttpServletRequest userinfo) ;
}
