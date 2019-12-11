package qwj.community.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;
import qwj.community.community.dto.QuestionDto;
import qwj.community.community.mapper.QuestionMapper;
import qwj.community.community.mapper.UserMapper;
import qwj.community.community.model.Question;
import qwj.community.community.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project
 * @Title QuestionService
 * @Description
 * @Author qwj
 * @Date 2019年12月10日 21:24
 */
@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDto> list() {
        ArrayList<QuestionDto> list = new ArrayList<>();
        List<Question> questionList = questionMapper.getList();
        for (Question question : questionList) {
            QuestionDto questionDto = new QuestionDto();
            User user = userMapper.findById(question.getCreator());
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            list.add(questionDto);
        }
        return list;
    }
}
