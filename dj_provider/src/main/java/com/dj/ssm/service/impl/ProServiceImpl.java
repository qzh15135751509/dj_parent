package com.dj.ssm.service.impl;

import com.dj.ssm.mapper.ProMapper;
import com.dj.ssm.pojo.Product;
import com.dj.ssm.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProServiceImpl implements ProService {

    @Autowired
    private ProMapper proMapper;

    @Override
    public List<Product> findProAll() throws Exception {
        return proMapper.findProAll();
    }

    @Override
    public Product findProByName(Product product) throws Exception {
        return proMapper.findProByName(product);
    }

    @Override
    public void addPro(Product product) throws Exception {
        proMapper.addPro(product);
    }

    @Override
    public Product findProById(Integer id) throws Exception {
        return proMapper.findProById(id);
    }

    @Override
    public void update(Product product) throws Exception {
        proMapper.update(product);
    }
}
