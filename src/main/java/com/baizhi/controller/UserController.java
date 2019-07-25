package com.baizhi.controller;

import com.baizhi.util.ImageCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

@RequestMapping("/user")
@Controller
public class UserController {


    @RequestMapping("getImageCode")
    public void getImageCode(HttpServletRequest request, HttpServletResponse response) {

        //设置响应类型
        response.setContentType("image/png");
        //获取验证码随机字符
        String code = ImageCodeUtil.getSecurityCode();
        //存验证码
        request.getSession().setAttribute("imageCode", code);
        //生成验证码
        BufferedImage image = ImageCodeUtil.createImage(code);

        try {
            //生成验证码图片
            ImageIO.write(image, "png", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("login")
    @ResponseBody
    public HashMap<String, Object> login(String username, String password) {

        System.out.println("username: " + username + " password: " + password);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success", 200);
        map.put("message", "成功");

        return map;
    }

    @RequestMapping("logout")
    public String logout() {

        System.out.println("退出");

        return "redirect:/login/login.jsp";
    }

}
