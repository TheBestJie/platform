package com.dao;

import com.daomain.Sp;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 查询商品表
     * @return 返回所有的商品数据
     */
    public List<Sp> findByPage(Integer start, Integer length){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Sp");
        query.setFirstResult(start);
        query.setMaxResults(length);
        return query.list();
    }

    public List<Sp> findAll(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Sp");
        return query.list();
    }

    public Integer total(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count(g) from Sp g");
        return ((Number)query.list().get(0)).intValue();
    }

    /**
     *  添加操作
     * @param sp 用户实体类
     */
    public void spSave(Sp sp){
        Session session = sessionFactory.getCurrentSession();
        session.save(sp);
    }

    /**
     * 删除操作
     * @param spbh 通过用户编号进行删除
     */
    public void spDelete(Integer spbh){
        Session session = sessionFactory.getCurrentSession();
        Sp sp = new Sp();
        sp.setSpbh(spbh);
        session.delete(sp);
    }

    /**
     * 查询操作
     * @param spnh 用户编号
     * @return 通过用户编号进行查询，返回用户信息
     */
    public Sp findSpById(Integer spnh){
        Session session = sessionFactory.openSession();
        return session.get(Sp.class, spnh);
    }

    /**
     * 修改操作
     * @param sp
     */
    public void spUpdate(Sp sp){
        Session session = sessionFactory.getCurrentSession();
        session.update(sp);
    }




}
