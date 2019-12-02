package qwj.community.community.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qwj.community.community.dto.AccesstokenDto;
import qwj.community.community.dto.GithubUser;
import qwj.community.community.provider.GithubProvider;

/**
 * @Project
 * @Title AuthorizeController
 * @Description
 * @Author qwj
 * @Date 2019年12月01日 22:38
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccesstokenDto accesstokenDto = new AccesstokenDto();
        accesstokenDto.setCode(code);
        accesstokenDto.setClient_id(clientId);
        accesstokenDto.setClient_secret(clientSecret);
        accesstokenDto.setRedirect_uri(redirectUri);
        accesstokenDto.setState(state);

        String accessToken = githubProvider.getAccessToken(accesstokenDto);
        System.out.println(accessToken);

        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(JSON.toJSONString(user));
        return "index";
    }
}
