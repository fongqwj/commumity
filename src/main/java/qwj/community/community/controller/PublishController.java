package qwj.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class PublishController {


    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
}
