package com.web.Model;

import com.web.Model.Libbook ;
/**
 * @Author: Jiang
 * @Date: Created in 15:50  2018\6\29 0029
 * @Description:
 * @Modified By:
 */
public class Libuser {
    private String id ;
    private String name ;

    public Libuser(){
        this.id = null ;
        this.name = null ;
    }

    public Libuser( String num , String name ){
        this.id = num ;
        this.name = name ;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name ;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
