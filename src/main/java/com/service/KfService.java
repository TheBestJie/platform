package com.service;

import com.dao.KfDao;
import com.daomain.Kf;
import com.daomain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KfService {

    @Autowired
    private KfDao kfDao;

    /**
     * 返回查询的分页信息
     * @param page
     * @param rows
     * @return
     */
    public Page findByPage(Integer page, Integer rows){
        Integer start = (page-1)*rows;
        Integer length = rows;
        List<Kf> kfList = kfDao.findByPage(start, length);
        long total = kfDao.total();
        return new Page(kfList,total);
    }

    /**
     * 批量添加数据
     * @param kfList
     */
    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saves(List<Kf> kfList){
        for (Kf kf : kfList) {
            kfDao.save(kf);
        }
    }

    /**
     * 查询所有的数据
     * @return
     */
    public List<Kf> findAll(){
        return kfDao.findAll();
    }

    /**
     * 添加单条数据
     * @param kf
     */
    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void save(Kf kf){
        kfDao.save(kf);
    }

    /**
     * 通过库房编号获取数据
     * @param kfbh
     * @return
     */
    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Kf findByKfbh(Integer kfbh){
        return kfDao.findByKfbh(kfbh);
    }

    /**
     * 修改一条数据
     * @param kf
     */
    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void kfEdit(Kf kf){
        kfDao.kfUpdate(kf);
    }

    /**
     * 删除一条记录
     * @param kfbh
     */
    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void kfDelete(Integer kfbh){
        kfDao.kfDelete(kfbh);
    }

}
