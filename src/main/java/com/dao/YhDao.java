package com.dao;

import com.daomain.Yh;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class YhDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 查询操作
     * @param yhm 用户名字段
     * @param yhmm 用户密码字段
     * @return 返回用户实体对象
     */
    public Yh findYhByNameAndPass(String yhm, String yhmm){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Yh where yhm = :yhm and yhmm = :yhmm");
        query.setParameter("yhm",yhm);
        query.setParameter("yhmm",yhmm);
        return (Yh) query.getSingleResult();
    }

    public Yh findByYhbh(Integer yhbh){
        Session session = sessionFactory.openSession();
        return session.get(Yh.class,yhbh);
    }
}
