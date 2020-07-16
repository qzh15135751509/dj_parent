package com.dj.ssm.mapper;

import com.dj.ssm.pojo.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProMapper {

    List<Product> findProAll() throws DataAccessException;

    void addPro(Product product) throws DataAccessException;

    Product findProByName(Product product) throws DataAccessException;

    Product findProById(Integer id) throws DataAccessException;

    void update(Product product) throws DataAccessException;
}
