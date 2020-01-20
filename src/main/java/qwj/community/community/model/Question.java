package qwj.community.community.model;

import lombok.Data;

/**
 * @Project
 * @Title Question
 * @Description
 * @Author qwj
 * @Date 2019年12月03日 20:48
 */
@Data
public class Question {

    private Integer id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private int creator;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private String tag;

}
