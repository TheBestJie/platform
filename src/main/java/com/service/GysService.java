package com.service;

import com.dao.GysDao;
import com.daomain.Gys;
import com.daomain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GysService {

    @Autowired
    private GysDao gysDao;

    /**
     * 分页查找
     * @param page
     * @param rows
     * @return
     */
    public Page gysList(Integer page, Integer rows){
        Integer start = (page-1)*rows;
        Integer length = rows;
        List<Gys> gysList = gysDao.findByPage(start, length);
        Long total = gysDao.total();
        return new Page(gysList,total);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saves(List<Gys> gysList){
        for (Gys gys : gysList) {
            gysDao.save(gys);
        }
    }

    public List<Gys> findAll(){
        return gysDao.findAll();
    }
}
