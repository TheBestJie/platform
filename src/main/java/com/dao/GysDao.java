package com.dao;

import com.daomain.Gys;
import com.daomain.Sp;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GysDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 获取分页数据
     * @param start
     * @param length
     * @return
     */
    public List<Gys> findByPage(Integer start, Integer length){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Gys");
        query.setFirstResult(start);
        query.setMaxResults(length);
        return query.list();
    }

    public List<Gys> findAll(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Gys");
        return query.list();
    }

    /**
     * 获取表格的总长度
     * @return
     */
    public Long total(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count(g) from Gys g");
        return ((Number) query.list().get(0)).longValue();
    }

    public void save(Gys gys){
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(gys);
    }
}
