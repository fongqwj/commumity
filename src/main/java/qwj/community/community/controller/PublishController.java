package qwj.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qwj.community.community.common.LoginUtils;
import qwj.community.community.model.Question;
import qwj.community.community.model.User;
import qwj.community.community.mapper.QuestionMapper;
import qwj.community.community.mapper.UserMapper;
import qwj.community.community.service.QuestionService;

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
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private LoginUtils loginUtils;

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id")Integer id,
                       Model model){
        Question question = questionMapper.getById(id);
        model.addAttribute("id",id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(HttpServletRequest httpServletRequest){
        User user = loginUtils.getUser(httpServletRequest);
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
        HttpServletRequest request,
        @RequestParam(name = "id",required = false)Integer id,
        @RequestParam(name = "title",required = false)String title,
        @RequestParam(name = "description",required = false)String description,
        @RequestParam(name = "tag",required = false)String tag,
        Model model){

        model.addAttribute("id",id);
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

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        questionService.createOrUpdate(question);

        return "redirect:/";
    }

}
