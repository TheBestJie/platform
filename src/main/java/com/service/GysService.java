package com.service;

import com.dao.GysDao;
import com.daomain.Gys;
import com.daomain.Page;
import com.util.ZjmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GysService {

    @Autowired
    private GysDao gysDao;

    /**
     * 分页查找
     * @param page
     * @param rows
     * @return
     */
    public Page gysList(Integer page, Integer rows){
        Integer start = (page-1)*rows;
        Integer length = rows;
        List<Gys> gysList = gysDao.findByPage(start, length);
        Long total = gysDao.total();
        return new Page(gysList,total);
    }

    /**
     * 批量添加数据
     * @param gysList
     */
    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saves(List<Gys> gysList){
        for (Gys gys : gysList) {
            gysDao.save(gys);
        }
    }

    /**
     * 获取所有数据
     * @return
     */
    public List<Gys> findAll(){
        return gysDao.findAll();
    }

    /**
     * 通过gysbh获取数据
     * @param gysbh
     * @return
     */
    public Gys findByGysbh(Long gysbh){
        return gysDao.findByGysbh(gysbh);
    }

    /**
     * 添加一条数据
     * @param gys
     */
    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void gysSave(Gys gys){
        gysDao.save(gys);
    }

    /**
     * 修改一条数据
     * @param gys
     */
    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void gysUpdate(Gys gys){
        gysDao.gysUpdate(gys);
    }

    /**
     * 删除一条数据
     * @param gysbh
     */
    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void gysDelete(Long gysbh){
        gysDao.gysDeleteByGysbh(gysbh);
    }
}
