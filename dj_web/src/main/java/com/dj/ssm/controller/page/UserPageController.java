package com.dj.ssm.controller.page;

import com.dj.ssm.pojo.User;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user/")
public class UserPageController {

    @Autowired
    private UserService userService;

    /**
     * 去登录
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin() {
        return "user/login";
    }

    /**
     * 去添加
     * @return
     */
    @RequestMapping("toAdd")
    public String toAdd() {
        return "user/add";
    }

    @RequestMapping("toShow")
    public String toShow(String token, Model model) {
        model.addAttribute("token",token);
        return "user/show";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(String token, Model model, Integer id) throws Exception {
        User user = userService.findUserById(id);
        model.addAttribute("token",token);
        model.addAttribute("user",user);
        return "user/update";
    }
}
