package com.dj.ssm.controller.page;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/index/")
public class IndexController {

	
	/**
	 * ȥindex.jsp
	 * 
	 * @return
	 */
	@RequestMapping("toIndex")
	public String toIndex(String token, Model model) {
		model.addAttribute("token",token);
		return "index/index";
	}

	/**
	 * ȥleft.jsp
	 * 
	 * @return
	 */
	@RequestMapping("toLeft")
	public String toLeft(String token, Model model) {
		model.addAttribute("token",token);
		return "index/left";
	}

	/**
	 * ȥright.jsp
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("toRight")
	public String toRight(String token, Model model) {
		model.addAttribute("token",token);
		return "index/right";
	}

	/**
	 * ȥtop.jsp
	 * 
	 * @return
	 */
	@RequestMapping("toTop")
	public String toTop(String token, Model model) {
		model.addAttribute("token",token);
		return "index/top";
	}

	/**
	 * 退出登录.jsp
	 *
	 * @return
	 */
	@RequestMapping("out")
	public String out(HttpSession session) {
		session.invalidate();
		return "user/login";
	}
	
	
}
