package book.controller;

import book.pojo.User;
import book.service.UserService;
import book.utils.LoggerUtils;
import book.utils.TransactionUtils;

import javax.servlet.http.HttpSession;

/**
 * @author: Ding
 * @date: 2022/6/29
 * @description:
 * @modify:
 */

public class UserController {

    private UserService userService;

    /**
     * 处理登录请求
     * @param username 用户名
     * @param pwd 密码
     * @param session session 作用域，用于保存用户在线信息
     * @return 返回 thymeleaf 应该渲染的页面路径
     */
    public String login(String username, String pwd, HttpSession session) {
        // 参数校验
        if (username == null || pwd == null) {
            LoggerUtils.logInfo("UserController#login()", username + " & " + pwd + " 传参异常");
            return "thymeleaf:user/login";
        }
        try {
            TransactionUtils.beginTransaction();
            User user = userService.login(username, pwd);
            if (user != null) {
                // 登录成功


                return "thymeleaf:user/login_success";
            }

        } catch (Exception e) {
            TransactionUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            TransactionUtils.commit();
        }

    }
}
