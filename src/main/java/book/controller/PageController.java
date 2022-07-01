package book.controller;

import book.pojo.Book;
import book.pojo.CartItem;
import book.pojo.Page;
import book.pojo.User;
import book.service.BookService;
import book.service.CartItemService;
import book.service.PageService;
import book.utils.LoggerUtils;
import book.utils.TransactionUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: Ding
 * @date: 2022/6/30
 * @description: 页面控制器，用于处理页面跳转的请求
 * @modify:
 */

public class PageController {

    private BookService bookService;

    private CartItemService cartItemService;

    private PageService pageService;

    public String toIndex(String isExit, String pageNum, HttpSession session) {
        try {
            TransactionUtils.beginTransaction();
            if ("true".equals(isExit)) {
                session.setAttribute("user", null);
            }

            // 获取每一页显示的图书数量
            String pageSize = session.getServletContext().getInitParameter("pageSize");
            Page page = pageService.getPage(pageNum, pageSize);

            if (page.getMaxPageNum() < page.getPageNum() || 1 > page.getPageNum()) {
                LoggerUtils.logInfo("访问的页码超出范围，将不进行数据更新");
                return "thymeleaf:index";
            }

            // 更新数据
            List<Book> bookList = bookService.selectAllByLimit(page.getPageSize(), page.getPageNum());
            session.setAttribute("page", page);
            session.setAttribute("bookList", bookList);
            return "thymeleaf:index";
        } catch (Exception e) {
            TransactionUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            TransactionUtils.commit();
        }
    }

    public String toLogin() {
        return "thymeleaf:user/login";
    }

    public String toRegist() {
        return "thymeleaf:user/regist";
    }

    public String toCartItem(HttpSession session) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            // 未登录
            return toLogin();
        }
        try {
            TransactionUtils.beginTransaction();
            User user = (User) userObj;

            // 更新数据
            List<CartItem> cartItemList = cartItemService.getAllCartItemsByUser(user);
            session.setAttribute("cartItemList", cartItemList);
            return "thymeleaf:cart/cart";
        } catch (Exception e) {
            TransactionUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            TransactionUtils.commit();
        }
    }

    public String toCheckout() {
        return "thymeleaf:cart/checkout";
    }

}
