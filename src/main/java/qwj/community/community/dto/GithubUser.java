package qwj.community.community.dto;

import lombok.Data;

/**
 * @Project
 * @Title GithubUser
 * @Description
 * @Author qwj
 * @Date 2019年12月01日 23:30
 */
@Data
public class GithubUser {

    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
