package qwj.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qwj.community.community.common.LoginUtils;
import qwj.community.community.dto.PaginationDto;
import qwj.community.community.model.User;
import qwj.community.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Project
 * @Title ProfileController
 * @Description
 * @Author qwj
 * @Date 2020年01月14日 21:16
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private LoginUtils loginUtils;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest httpServletRequest,
                          @PathVariable(name = "action")String action,
                          Model model,
                          @RequestParam(name = "page",defaultValue = "1") int page,
                          @RequestParam(name = "size",defaultValue = "3") int size){
        User user = loginUtils.getUser(httpServletRequest);
        if (user == null) {
            return "index";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
        }else if ("reply".equals(action)){
            model.addAttribute("section","reply");
            model.addAttribute("sectionName","最新回复");
        }

        PaginationDto paginationDto = questionService.list(user.getId(),page,size);
        model.addAttribute("paginationDto",paginationDto);
        return "profile";
    }
}
