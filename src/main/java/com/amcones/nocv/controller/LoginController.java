package com.amcones.nocv.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.amcones.nocv.entity.User;
import com.amcones.nocv.service.UserService;
import com.amcones.nocv.view.DataView;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login/getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(116,36,4,10);
        session.setAttribute("code",captcha.getCode());
        ServletOutputStream outputStream = response.getOutputStream();
        captcha.write(outputStream);
        outputStream.close();
    }

    @RequestMapping("/login/login")
    @ResponseBody
    public DataView login(String username,String password,String code,HttpSession session){
        DataView dataView = new DataView();

        String sessionCode = (String)session.getAttribute("code");
        if(code!=null && sessionCode.equals(code)){
//            User user = userService.login(username,password);
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            User user = (User)subject.getPrincipal();
            if(user!=null){
                dataView.setCode(200);
                dataView.setMsg("登录成功");
                session.setAttribute("user",user);
                return dataView;
            }else{
                dataView.setCode(100);
                dataView.setMsg("用户名或密码错误");
                return dataView;
            }
        }
        dataView.setCode(100);
        dataView.setMsg("验证码错误");
        return dataView;
    }

    @RequestMapping("/tologout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
