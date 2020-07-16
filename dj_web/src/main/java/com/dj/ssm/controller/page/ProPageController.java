package com.dj.ssm.controller.page;

import com.dj.ssm.pojo.Product;
import com.dj.ssm.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pro/")
public class ProPageController {

    @Autowired
    private ProService proService;

    @RequestMapping("toShow")
    public String toShow(String token, Model model) {
        model.addAttribute("token",token);
        return "pro/show";
    }

    @RequestMapping("toAdd")
    public String toAdd(String token, Model model) {
        model.addAttribute("token",token);
        return "pro/add";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(String token, Model model, Integer id) throws Exception {
        Product pro = proService.findProById(id);
        model.addAttribute("token",token);
        model.addAttribute("pro",pro);
        return "pro/update";
    }
}
