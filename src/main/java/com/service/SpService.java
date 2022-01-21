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

@Service
public class SpService {

    @Autowired
    private SpDao spDao;

    public List<Sp> spList(){
        return spDao.findSpAll();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void spSave(Sp sp){
        String zjm = ZjmUtil.ToFirstChar(sp.getSpmc());
        sp.setZjm(zjm);
        spDao.spSave(sp);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void spDelete(Integer spbh){
        spDao.spDelete(spbh);
    }

    public Sp spEdit(Integer spbh){
        return spDao.findSpById(spbh);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void spUpdate(Sp sp){
        String zjm = ZjmUtil.ToFirstChar(sp.getSpmc());
        sp.setZjm(zjm);
        spDao.spUpdate(sp);
    }
}
