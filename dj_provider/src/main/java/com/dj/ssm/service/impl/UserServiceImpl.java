package com.dj.ssm.service.impl;

import com.dj.ssm.mapper.UserMapper;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByNameAndPwd(User user) throws Exception {
        return userMapper.findUserByNameAndPwd(user);
    }

    @Override
    public User findUserByName(User user) throws Exception {
        return userMapper.findUserByName(user);
    }

    @Override
    public void addUser(User user) throws Exception {
        userMapper.addUser(user);
    }

    @Override
    public User findUserById(Integer id) throws Exception {
        return userMapper.findUserById(id);
    }

    @Override
    public List<User> findUserAll() throws Exception {
        return userMapper.findUserAll();
    }

    @Override
    public void update(User user) throws Exception {
        userMapper.update(user);
    }
}
