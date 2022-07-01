package book.controller;

import book.pojo.User;
import book.service.CartItemService;
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

    private CartItemService cartItemService;

    /**
     * 处理登录请求
     * @param uname 用户名
     * @param pwd 密码
     * @param session session 作用域，用于保存用户在线信息
     * @return 返回 thymeleaf 应该渲染的页面路径
     */
    public String login(String uname, String pwd, HttpSession session) {
        // 参数校验
        if (uname == null || pwd == null) {
            LoggerUtils.logInfo(uname + " & " + pwd + " 传参异常");
            return "thymeleaf:user/login";
        }
        try {
            TransactionUtils.beginTransaction();
            User user = userService.login(uname, pwd);
            if (user != null) {
                // 登录成功
                LoggerUtils.logInfo(user.getId() + " 号用户 " + user.getUsername() + " 登陆成功");

                session.setAttribute("user", user);

                return "thymeleaf:user/login_success";
            } else {
                // 登录失败
                LoggerUtils.logInfo("uname: " + uname + " pwd: " + pwd + " 登录失败");
                return "thymeleaf:user/login";
            }

        } catch (Exception e) {
            TransactionUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            TransactionUtils.commit();
        }
    }


    public String register(String username, String password, String email, HttpSession session) {
        if (username == null || password == null || email == null) {
            return "thymeleaf:user/regist";
        }

        try {
            TransactionUtils.beginTransaction();

            User user = userService.register(username, password, email);
            session.setAttribute("newUser", user);
            if (user.getId() != 1) {
                return "thymeleaf:user/regist_success";
            } else {
                return "thymeleaf:user/regist";
            }
        } catch (Exception e) {
            TransactionUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            TransactionUtils.commit();
        }
    }
}
