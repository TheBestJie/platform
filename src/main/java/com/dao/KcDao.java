package com.dao;

import com.daomain.Kc;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KcDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Kc kc){
        Session session = sessionFactory.getCurrentSession();
        session.save(kc);
    }
}
