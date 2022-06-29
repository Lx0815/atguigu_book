package book.service;

import book.mapper.UserMapper;
import book.pojo.User;
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
}
