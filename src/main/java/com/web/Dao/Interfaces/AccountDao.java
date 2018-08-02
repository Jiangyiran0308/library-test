package com.web.Dao.Interfaces;

import com.web.Model.*;

public interface AccountDao {
    Account getAccountByName(String name,String accountPass);
}
