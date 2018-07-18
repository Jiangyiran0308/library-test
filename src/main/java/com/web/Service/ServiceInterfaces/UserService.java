package com.web.Service.ServiceInterfaces;

import com.web.Model.Libuser;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: Jiang
 * @Date: Created in 16:55  2018\6\29 0029
 * @Description:
 * @Modified By:
 */


public interface UserService {

    void add(HttpServletRequest userinfo);

    void update(HttpServletRequest userinfo);

    void delete(HttpServletRequest userinfo);

    List<Libuser> searchAll();

    Libuser searchByIDorName(HttpServletRequest userinfo);

}
