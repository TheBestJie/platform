package com.service;

import com.dao.*;
import com.daomain.*;
import com.util.JhdbhUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JhdService {

    @Autowired
    private JhdDao jhdDao;
    @Autowired
    private GysDao gysDao;
    @Autowired
    private KfDao kfDao;
    @Autowired
    private YhDao yhDao;
    @Autowired
    private SpDao spDao;
    @Autowired
    private KcDao kcDao;

    //通过商品名称查询
    public List<Sp> findSpByMc(String spmc){
        return jhdDao.findSpByMc(spmc);
    }

    /**
     * 保存进货单
     * @param jhd
     * @param spbhs
     * @param spsls
     * @param spjgs
     */
    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public synchronized void jhdSave(Jhd jhd, String spbhs, String spsls, String spjgs){
        //获取最大的进货单号
        String old_jhdbh = jhdDao.findMaxJhdbh();
        //获取当前进货单编号,并存入
        String jhdbh = JhdbhUtil.getJhdbh(old_jhdbh);
        jhd.setJhdbh(jhdbh);
        //获取数据存入
        Gys gys = gysDao.findByGysbh(jhd.getGysbh());
        jhd.setGys(gys);
        Kf kf = kfDao.findByKfbh(jhd.getKfbh());
        jhd.setKf(kf);
        Yh yh = yhDao.findByYhbh(jhd.getJhrbh());
        jhd.setJhr(yh);

        //存入集合
        Set<Jhxq> jhxqList = new HashSet<>();
        String[] spbhArray = spbhs.split(",");
        String[] spslArray = spsls.split(",");
        String[] spjgArray = spjgs.split(",");
        for (int i = 0; i < spbhArray.length; i++) {
            int spbh = Integer.parseInt(spbhArray[i]);
            int spsl = Integer.parseInt(spslArray[i]);
            int spjg = Integer.parseInt(spjgArray[i]);

            //将进货详情的数据存储到进货详情的实体中
            Jhxq jhxq = new Jhxq();
            jhxq.setJhdbh(jhd.getJhdbh());
            jhxq.setSpbh(spbh);
            Sp sp = new Sp();
            sp.setSpbh(spbh);
            jhxq.setSp(sp);
            jhxq.setSpjg(spjg);
            jhxq.setSpsl(spsl);
            jhxqList.add(jhxq);

            //存储库房
            Kc kc = new Kc();
            kc.setKfbh(jhd.getKfbh());
            kc.setSpbh(spbh);
            kc.setSpsl(spsl);
            kc.setKf(kf);
            kc.setSp(spDao.findSpById(spbh));
            kcDao.save(kc);
        }
        jhd.setJhxqList(jhxqList);
        jhdDao.saveJhd(jhd);
    }

}
