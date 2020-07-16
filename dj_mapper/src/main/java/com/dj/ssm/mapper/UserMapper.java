package com.dj.ssm.mapper;

import com.dj.ssm.pojo.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserMapper {

    User findUserByNameAndPwd(User user) throws DataAccessException;

    User findUserByName(User user) throws DataAccessException;

    void addUser(User user) throws DataAccessException;

    User findUserById(Integer id) throws DataAccessException;

    List<User> findUserAll() throws DataAccessException;

    void update(User user) throws DataAccessException;
}
