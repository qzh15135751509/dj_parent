package com.dj.ssm.service;

import com.dj.ssm.pojo.User;

import java.util.List;

public interface UserService {

    User findUserByNameAndPwd(User user) throws Exception;

    User findUserByName(User user) throws Exception;

    void addUser(User user) throws Exception;

    User findUserById(Integer id) throws Exception;

    List<User> findUserAll() throws Exception;

    void update(User user) throws Exception;
}
