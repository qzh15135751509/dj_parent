package com.dj.ssm.mapper;

import com.dj.ssm.pojo.Token;
import org.springframework.dao.DataAccessException;

public interface TokenMapper {

    Token findByUserId(Integer userId) throws DataAccessException;

    void add(Token token) throws DataAccessException;

    void update(Token token) throws DataAccessException;

    Token findByToken(String token) throws DataAccessException;
}
