package qwj.community.community.dto;

import lombok.Data;
import qwj.community.community.model.User;

/**
 * @Project
 * @Title QuestionDto
 * @Description
 * @Author qwj
 * @Date 2019年12月10日 21:27
 */
@Data
public class QuestionDto {

    private int id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private int creator;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private String tag;
    private User user;
}
