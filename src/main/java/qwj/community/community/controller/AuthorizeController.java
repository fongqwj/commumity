package qwj.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qwj.community.community.dto.AccesstokenDto;
import qwj.community.community.dto.GithubUser;
import qwj.community.community.dto.User;
import qwj.community.community.mapper.UserMapper;
import qwj.community.community.provider.GithubProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse){

        // 1.在页面上请求访问后，返回code
        // 2.再次传入code以及必传参数等，获得token
        AccesstokenDto accesstokenDto = new AccesstokenDto();
        accesstokenDto.setCode(code);
        accesstokenDto.setClient_id(clientId);
        accesstokenDto.setClient_secret(clientSecret);
        accesstokenDto.setRedirect_uri(redirectUri);
        accesstokenDto.setState(state);
        String accessToken = githubProvider.getAccessToken(accesstokenDto);

        // 3.传入token，返回用户信息
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(githubUser.getId().toString());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            user.setBio(githubUser.getBio());
            userMapper.insert(user);
            //登录成功，写入cookie
            httpServletResponse.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else {
            //登录失败
            return "redirect:/";
        }
    }
}
