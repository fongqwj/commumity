package qwj.community.community.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import qwj.community.community.model.User;
import qwj.community.community.mapper.UserMapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Project
 * @Title LoginUtils
 * @Description  登录工具类
 * @Author qwj
 * @Date 2019年12月03日 21:59
 */
@Component
public class LoginUtils {

    @Autowired
    private UserMapper userMapper;

    public User getUser(HttpServletRequest httpServletRequest) {
        User user = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null && cookies.length != 0){
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        httpServletRequest.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        if (user == null) {
            httpServletRequest.getSession().setAttribute("user",null);
        }
        return user;
    }
}
