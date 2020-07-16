package com.dj.ssm.controller;

import com.dj.ssm.pojo.Product;
import com.dj.ssm.pojo.ResultModel;
import com.dj.ssm.pojo.Token;
import com.dj.ssm.service.ProService;
import com.dj.ssm.service.TokenService;
import com.dj.ssm.utils.SysConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pro/")
public class ProController {

    @Autowired
    private ProService proService;

    @Autowired
    TokenService tokenService;

    @RequestMapping("show")
    public ResultModel<Object> show(Integer pageNo) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            PageHelper.startPage(pageNo, SysConstant.INTEGER_PAGE);
            List<Product> list = proService.findProAll();
            PageInfo<Product> pageInfo = new PageInfo<Product>(list);
            map.put("list", pageInfo.getList());
            map.put("pages", pageInfo.getPages());
            return new ResultModel<Object>().success(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("服务器异常,请稍后重试");
        }
    }

    @RequestMapping("findProByName")
    public Boolean findProByName(Product product) {
        try {
            Product pro = proService.findProByName(product);
            if (null == pro) {
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
    public ResultModel<Object> add(Product product, String token) {
        try {
            Token token1 = tokenService.findByToken(token);
            product.setIsDel(SysConstant.INTEGER_ISDEL_1);
            product.setUserId(token1.getUserId());
            proService.addPro(product);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("服务器异常,请稍后重试");
        }
    }

    @RequestMapping("update")
    public ResultModel<Object> update(Product product) {
        try {
            if(StringUtils.isEmpty(product.getProName())) {
                return new ResultModel<Object>().error("不能为空");
            }
            proService.update(product);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("服务器异常,请稍后重试");
        }
    }

    @RequestMapping("updateIsDel")
    public ResultModel<Object> updateIsDel(Product product) {
        try {
            proService.update(product);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("服务器异常,请稍后重试");
        }
    }
}
