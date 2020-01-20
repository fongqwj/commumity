package qwj.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import qwj.community.community.dto.QuestionDto;
import qwj.community.community.mapper.QuestionMapper;
import qwj.community.community.model.Question;
import qwj.community.community.service.QuestionService;

/**
 * @Project
 * @Title QuestionController
 * @Description
 * @Author qwj
 * @Date 2020年01月17日 10:52
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String profile(@PathVariable(value = "id")Integer id,
                          Model model){

        QuestionDto question = questionService.getById(id);
        model.addAttribute("question",question);
        return "question";
    }

}
