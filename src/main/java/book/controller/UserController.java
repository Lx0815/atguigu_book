package book.controller;

import book.pojo.User;
import book.service.UserService;
import book.utils.LoggerUtils;

import javax.servlet.http.HttpSession;

/**
 * @author: Ding
 * @date: 2022/6/29
 * @description:
 * @modify:
 */

public class UserController {

    private UserService userService;

    public String login(String username, String pwd, HttpSession session) {
        if (username == null || pwd == null) {
            LoggerUtils.logInfo("UserController#login()", username + " & " + pwd + " 传参异常");
            return "thymeleaf:user/login";
        }
        User user = userService.login(username, pwd);
        return null;
    }
}
