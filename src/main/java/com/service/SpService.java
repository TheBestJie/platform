package com.service;

import com.dao.SpDao;
import com.daomain.Sp;
import com.util.ZjmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品信息相关的业务处理类
 */
@Service
public class SpService {

    @Autowired
    private SpDao spDao;

    /**
     * 查询所有的商品信息
     * @return 返回查询结果
     */
    public List<Sp> spList(){
        return spDao.findSpAll();
    }

    /**
     * 添加一条商品信息
     * @param sp
     */
    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void spSave(Sp sp){
        //通过工具类将商品名称的首字母作为助记码
        String zjm = ZjmUtil.ToFirstChar(sp.getSpmc());
        sp.setZjm(zjm);
        spDao.spSave(sp);
    }

    /**
     * 删除商品信息操作
     * @param spbh 通过商品编号进行删除
     */
    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void spDelete(Integer spbh){
        spDao.spDelete(spbh);
    }

    /**
     * 查询操作
     * @param spbh 通过商品编号进行查询
     * @return 返回查询的结果
     */
    public Sp spEdit(Integer spbh){
        return spDao.findSpById(spbh);
    }

    /**
     * 修改商品信息
     * @param sp
     */
    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void spUpdate(Sp sp){
        String zjm = ZjmUtil.ToFirstChar(sp.getSpmc());
        sp.setZjm(zjm);
        spDao.spUpdate(sp);
    }
}
