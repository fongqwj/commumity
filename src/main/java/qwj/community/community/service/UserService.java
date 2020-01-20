package qwj.community.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qwj.community.community.mapper.UserMapper;
import qwj.community.community.model.User;

/**
 * @Project
 * @Title UserService
 * @Description
 * @Author qwj
 * @Date 2020年01月20日 11:07
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void createOrUpdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if (dbUser == null) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            userMapper.insert(user);
        }else {
            user.setGmtModified(System.currentTimeMillis());
            userMapper.update(user);
        }
    }
}
