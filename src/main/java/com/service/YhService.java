package com.service;

import com.dao.YhDao;
import com.daomain.Yh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YhService {

    @Autowired
    private YhDao yhDao;

    public Yh checkLogin(String yhm, String yhmm){
        return yhDao.findYhByNameAndPass(yhm,yhmm);
    }
}
