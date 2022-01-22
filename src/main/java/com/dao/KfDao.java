package com.dao;

import com.daomain.Kf;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KfDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 分页查询
     * @param start
     * @param length
     * @return
     */
    public List<Kf> findByPage(Integer start, Integer length){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Kf");
        query.setFirstResult(start);
        query.setMaxResults(length);
        return query.list();
    }

    /**
     * 查看总条数
     * @return
     */
    public Integer total(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count(g) from Kf g");
        return ((Number) query.list().get(0)).intValue();
    }

    /**
     * 添加数据
     * @param kf
     */
    public void save(Kf kf){
        Session session = sessionFactory.getCurrentSession();
        session.save(kf);
    }

    /**
     * 查询全部数据
     * @return
     */
    public List<Kf> findAll(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Kf");
        return query.list();
    }

    /**
     * 通过kfbh查询数据
     * @param kfbh
     * @return
     */
    public Kf findByKfbh(Integer kfbh){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Kf.class,kfbh);
    }

    /**
     * 修改一条数据
     * @param kf
     */
    public void kfUpdate(Kf kf){
        Session session = sessionFactory.getCurrentSession();
        session.update(kf);
    }

    /**
     * 删除一条记录
     * @param kfbh
     */
    public void kfDelete(Integer kfbh){
        Session session = sessionFactory.getCurrentSession();
        Kf kf = new Kf();
        kf.setKfbh(kfbh);
        session.delete(kf);
    }

}
