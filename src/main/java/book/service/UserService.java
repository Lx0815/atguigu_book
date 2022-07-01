package book.service;

import book.mapper.UserMapper;
import book.pojo.User;
import book.utils.LoggerUtils;
import book.utils.TransactionUtils;

/**
 * @author: Ding
 * @date: 2022/6/29
 * @description:
 * @modify:
 */

public class UserService {
    public User login(String username, String pwd) {
        UserMapper mapper = TransactionUtils.getMapper(UserMapper.class);
        return mapper.selectByUsernameAndPassword(username, pwd);
    }

    public User register(String username, String password, String email) {
        User user = new User(username, password, email, 0);
        UserMapper mapper = TransactionUtils.getMapper(UserMapper.class);
        user.setId(mapper.insertOneByUser(user));
        LoggerUtils.logInfo("有新用户注册，新用户 id 为 " + user.getId());
        return user;
    }
}
