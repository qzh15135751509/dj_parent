package com.dj.ssm.controller;


import com.dj.ssm.pojo.ResultModel;
import com.dj.ssm.pojo.Token;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.TokenService;
import com.dj.ssm.service.UserService;
import com.dj.ssm.utils.SysConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping("login")
    public ResultModel<Object> login(User user) {
        try {
            User u = userService.findUserByNameAndPwd(user);
            if (null == u) {
                return new ResultModel<>().error("该用户不存在");
            }
            if(u.getIsDel() == SysConstant.INTEGER_ISDEL_0) {
                return new ResultModel<>().error("该用户已失效");
            }
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.MINUTE, SysConstant.INTEGER_MINUTES);
            Token token = tokenService.findByUserId(u.getId());
            String tokenUser = UUID.randomUUID().toString().replace("-", "");
            if(null == token) {
                Token t = new Token()
                        .setToken(tokenUser)
                        .setUserId(u.getId())
                        .setValidateTime(c.getTime());
                tokenService.add(t);
            } else {
                token.setToken(tokenUser);
                token.setValidateTime(c.getTime());
                tokenService.update(token);
            }
            Map<String,Object> map = new HashMap<>();
            map.put("token",tokenUser);
            return new ResultModel<>().success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常,请稍后重试");
        }
    }

    @RequestMapping("show")
    public ResultModel<Object> show(Integer pageNo) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            PageHelper.startPage(pageNo, SysConstant.INTEGER_PAGE);
            List<User> list = userService.findUserAll();
            PageInfo<User> pageInfo = new PageInfo<User>(list);
            map.put("list", pageInfo.getList());
            map.put("pages", pageInfo.getPages());
            return new ResultModel<Object>().success(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("服务器异常,请稍后重试");
        }
    }

    @RequestMapping("findUserByName")
    public Boolean findUserByName(User user) {
        try {
            User u = userService.findUserByName(user);
            if (null == u) {
                return true;
            }
            return false;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("add")
    public ResultModel<Object> add(User user) {
        try {
            user.setIsDel(SysConstant.INTEGER_ISDEL_1);
            userService.addUser(user);
            return new ResultModel<>().success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常,请稍后重试");
        }
    }

    @RequestMapping("update")
    public ResultModel<Object> update(User user) {
        try {
            if(StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getUserPwd().isEmpty())) {
                return new ResultModel<>().error("不能为空");
            }
            userService.update(user);
            return new ResultModel<>().success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常,请稍后重试");
        }
    }

    @RequestMapping("updateIsDel")
    public ResultModel<Object> updateIsDel(User user) {
        try {
            userService.update(user);
            return new ResultModel<>().success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常,请稍后重试");
        }
    }
}
