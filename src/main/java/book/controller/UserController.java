package book.controller;

import book.pojo.CartItem;
import book.pojo.User;
import book.service.CartItemService;
import book.service.UserService;
import book.utils.LoggerUtils;
import book.utils.TransactionUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

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
                LoggerUtils.logInfo(user.getId() + " 登陆成功");
                List<CartItem> cartItemList = cartItemService.getAllCartItemsByUser(user);
                session.setAttribute("user", user);
                session.setAttribute("cartItemList", cartItemList);
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
}
