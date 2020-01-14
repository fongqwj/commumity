package qwj.community.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;
import qwj.community.community.dto.PaginationDto;
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

    //主页列表
    public PaginationDto list(int page, int size) {
        PaginationDto paginationDto = getPaginationDto(null,page, size);
        return paginationDto;
    }

    private PaginationDto getPaginationDto(Integer userId, int page, int size) {
        PaginationDto paginationDto = new PaginationDto();
        //总条数
        Integer totalCount;
        if (userId == null) {
            totalCount = questionMapper.count();
        }else {
            totalCount = questionMapper.countByUserId(userId);
        }
        paginationDto.setPagination(totalCount,page,size);
        if (page < 1) {
            page = 1;
        }
        if (page > paginationDto.getTotalPage()) {
            page = paginationDto.getTotalPage();
        }
        Integer offset = size * (page - 1);

        ArrayList<QuestionDto> list = new ArrayList<>();
        List<Question> questionList;
        if (userId == null) {
            questionList = questionMapper.getList(offset,size);
        }else {
            questionList = questionMapper.getListByUserId(userId,offset,size);
        }

        for (Question question : questionList) {
            QuestionDto questionDto = new QuestionDto();
            User user = userMapper.findById(question.getCreator());
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            list.add(questionDto);
        }
        paginationDto.setQuestions(list);
        return paginationDto;
    }

    //我的回复列表
    public PaginationDto list(int id, int page, int size) {
        PaginationDto paginationDto = getPaginationDto(id,page, size);
        return paginationDto;
    }
}
