package com.dao;

import com.daomain.Jhd;
import com.daomain.Sp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JhdDao {

    @Autowired
    private SessionFactory sessionFactory;

    //通过商品名称查询
    //使用模糊查询 可以通过商品名称也可以通过助记码
    public List<Sp> findSpByMc(String spmc){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Sp where spmc like ?1 or zjm like ?2");
        query.setParameter(1,spmc+"%");
        query.setParameter(2,spmc+"%");
        return query.list();
    }

    //获取最大的订单号
    public String findMaxJhdbh(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select max(jhdbh) from Jhd");
        return (String) query.uniqueResult();
    }

    //存储操作
    public void saveJhd(Jhd jhd){
        Session session = sessionFactory.getCurrentSession();
        session.save(jhd);
    }
}
