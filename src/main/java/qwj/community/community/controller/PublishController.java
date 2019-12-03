package qwj.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qwj.community.community.dto.Question;
import qwj.community.community.dto.User;
import qwj.community.community.mapper.QuestionMapper;
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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
        HttpServletRequest httpServletRequest,
        @RequestParam(name = "title")String title,
        @RequestParam(name = "description")String description,
        @RequestParam(name = "tag")String tag,
        Model model){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if (title == null || "".equals(title)) {
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (description == null || "".equals(description)) {
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        if (tag == null || "".equals(tag)) {
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if (user != null) {
                    httpServletRequest.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        if (user == null) {
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(System.currentTimeMillis());
        questionMapper.insert(question);

        return "redirect:/";
    }
}
