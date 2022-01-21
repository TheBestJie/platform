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

    public Yh findYhByNameAndPass(String yhm, String yhmm){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Yh where yhm = :yhm and yhmm = :yhmm");
        query.setParameter("yhm",yhm);
        query.setParameter("yhmm",yhmm);
        return (Yh) query.getSingleResult();
    }
}
