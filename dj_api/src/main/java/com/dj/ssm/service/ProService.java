package com.dj.ssm.service;

import com.dj.ssm.pojo.Product;

import java.util.List;

public interface ProService {

    List<Product> findProAll() throws Exception;

    Product findProByName(Product product) throws Exception;

    void addPro(Product product) throws Exception;

    Product findProById(Integer id) throws Exception;

    void update(Product product) throws Exception;
}
