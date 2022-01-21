package com.dao;

import com.daomain.Sp;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Sp> findSpAll(){
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Sp.class);
        return criteria.list();
    }

    public void spSave(Sp sp){
        Session session = sessionFactory.getCurrentSession();
        session.save(sp);
    }

    public void spDelete(Integer spbh){
        Session session = sessionFactory.getCurrentSession();
        Sp sp = new Sp();
        sp.setSpbh(spbh);
        session.delete(sp);
    }

    public Sp findSpById(Integer spnh){
        Session session = sessionFactory.openSession();
        return session.get(Sp.class, spnh);
    }

    public void spUpdate(Sp sp){
        Session session = sessionFactory.getCurrentSession();
        session.update(sp);
    }



}
