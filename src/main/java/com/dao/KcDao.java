package com.dao;

import com.daomain.Kc;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class KcDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveOrUpdate(Kc kc){
        Session session = sessionFactory.getCurrentSession();
        session.merge(kc); //与saveOrUpdate方法类似，有主键及修改，无主键及新增
    }

    public Kc findKcByKfAndSp(Integer kfbh, Integer spbh){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Kc where kfbh = :kfbh and spbh = :spbh");
        query.setParameter("kfbh",kfbh);
        query.setParameter("spbh",spbh);
        //return (Kc) query.uniqueResult();
        //与上述方法类似，现在更推荐使用此方法查询
        //不同的是，使用此方法没有查询到数据会抛出NoResultException异常
        try {
            return (Kc) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
