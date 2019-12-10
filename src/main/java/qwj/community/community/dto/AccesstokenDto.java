package qwj.community.community.dto;

import lombok.Data;

/**
 * @Project
 * @Title AccesstokenDto
 * @Description
 * @Author qwj
 * @Date 2019年12月01日 22:46
 */
@Data
public class AccesstokenDto {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
