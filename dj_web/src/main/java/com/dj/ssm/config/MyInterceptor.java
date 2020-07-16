package com.dj.ssm.config;

import com.dj.ssm.pojo.Token;
import com.dj.ssm.service.TokenService;
import com.dj.ssm.utils.SysConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        if (null == user) {
//            response.sendRedirect(
//                    request.getContextPath() + "/user/toLogin");
//            return false;
//        }
        String token = request.getParameter("token");
        if(null == token) {
            response.sendRedirect(
                    request.getContextPath() + "/user/toLogin");
            return false;
        }
        Token token1 = tokenService.findByToken(token);
        if(null == token1) {
            response.sendRedirect(
                    request.getContextPath() + "/user/toLogin");
            return false;
        }
        if(new Date().getTime() > token1.getValidateTime().getTime()) {
            response.sendRedirect(
                    request.getContextPath() + "/user/toLogin");
            return false;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MINUTE, SysConstant.INTEGER_MINUTES);
        token1.setValidateTime(c.getTime());
        tokenService.update(token1);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
