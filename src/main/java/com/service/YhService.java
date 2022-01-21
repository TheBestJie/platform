package com.service;

import com.dao.YhDao;
import com.daomain.Yh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YhService {

    @Autowired
    private YhDao yhDao;

    /**
     * 验证用户登录的信息
     * @param yhm 用户名
     * @param yhmm 用户密码
     * @return 返回查询的结果，有数据则返回用户实体类，没有则返回null
     */
    public Yh checkLogin(String yhm, String yhmm){
        return yhDao.findYhByNameAndPass(yhm,yhmm);
    }
}
