package qwj.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Project
 * @Title HelloController
 * @Description
 * @Author qwj
 * @Date 2019年12月01日 16:54
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name",name);
        return "index";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
