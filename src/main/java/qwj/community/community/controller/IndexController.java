package qwj.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qwj.community.community.common.LoginUtils;
import qwj.community.community.dto.User;
import qwj.community.community.mapper.UserMapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Project
 * @Title HelloController
 * @Description
 * @Author qwj
 * @Date 2019年12月01日 16:54
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginUtils loginUtils;

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name",name);
        return "index";
    }

    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest){
        User user = loginUtils.getUser(httpServletRequest);
        if (user == null) {
            httpServletRequest.getSession().setAttribute("user",null);
        }
        return "index";
    }
}
