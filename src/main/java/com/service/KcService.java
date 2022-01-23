package com.service;

import com.dao.KcDao;
import com.daomain.Kc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KcService {

    @Autowired
    private KcDao kcDao;


}
