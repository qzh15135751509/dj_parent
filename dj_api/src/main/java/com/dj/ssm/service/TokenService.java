package com.dj.ssm.service;


import com.dj.ssm.pojo.Token;

public interface TokenService {

    Token findByUserId(Integer userId) throws Exception;

    void add(Token token) throws Exception;

    void update(Token token) throws Exception;

    Token findByToken(String token) throws Exception;
}
