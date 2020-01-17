package qwj.community.community.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import qwj.community.community.common.LoginUtils;
import qwj.community.community.mapper.UserMapper;
import qwj.community.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Project
 * @Title SessionInterceptor
 * @Description
 * @Author qwj
 * @Date 2020年01月16日 16:47
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginUtils loginUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = loginUtils.getUser(request);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
