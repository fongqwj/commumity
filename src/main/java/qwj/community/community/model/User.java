package qwj.community.community.model;

import lombok.Data;

/**
 * @Project
 * @Title User
 * @Description
 * @Author qwj
 * @Date 2019年12月02日 15:45
 */
@Data
public class User {
    private int id;
    private String accountId;
    private String name;
    private String token;
    private long gmtCreate;
    private long gmtModified;
    private String avatarUrl;
    private String bio;

}
