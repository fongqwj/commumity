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
    //标题
    private String title;
    //内容
    private String description;
    //创建时间
    private long gmtCreate;
    //修改时间
    private long gmtModified;
    //创建人id
    private int creator;
    //回复数
    private int commentCount;
    //访问数
    private int viewCount;
    //喜欢数
    private int likeCount;
    //标签
    private String tag;
    //用户
    private User user;
}
