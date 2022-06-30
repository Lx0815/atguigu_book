package book.controller;

import book.pojo.Book;
import book.pojo.CartItem;
import book.pojo.Page;
import book.pojo.User;
import book.service.BookService;
import book.service.CartItemService;
import book.utils.LoggerUtils;
import book.utils.TransactionUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: Ding
 * @date: 2022/6/30
 * @description:
 * @modify:
 */

public class PageController {

    private BookService bookService;

    private CartItemService cartItemService;

    public String toIndex(String pageNum, HttpSession session) {
        try {
            TransactionUtils.beginTransaction();
//            List<Book> bookList = bookService.selectAll();
            // 获取每一页显示的图书数量
            String pageSize = session.getServletContext().getInitParameter("pageSize");
            if (pageSize == null) {
                pageSize = "15";
                LoggerUtils.logInfo("web.xml 未指定 pageSize，已赋默认值 15");
            }
            // 获取当前页数
            if (pageNum == null) {
                pageNum = "1";
            }
            // 封装 page 对象
            Page page = new Page();
            page.setPageNum(Integer.parseInt(pageNum));
            page.setPageSize(Integer.parseInt(pageSize));

            Integer allCount = bookService.selectAllCount();
            page.setAllCount(allCount);
            page.setMaxPageNum(allCount / page.getPageSize() + 1);

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
            return toLogin();
        }
        try {
            TransactionUtils.beginTransaction();
            User user = (User) userObj;
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
