package book.controller;

import book.pojo.*;
import book.service.BookService;
import book.service.CartItemService;
import book.service.OrderService;
import book.service.PageService;
import book.utils.LoggerUtils;
import book.utils.TransactionUtils;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
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

    private OrderService orderService;

    public String toIndex(String isExit, String pageNum, HttpSession session) {
        if ("true".equals(isExit)) {
            session.setAttribute("user", null);
        }
        return filterByPrice(pageNum, null, null, session);
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

    public String filterByPrice(String pageNum, String priceBottom, String priceTop, HttpSession session) {
        if (priceBottom == null) priceBottom = "0";
        if (pageNum == null) pageNum = "1";
        if (priceTop == null) priceTop = "99999999999999";
        try {
            TransactionUtils.beginTransaction();
            String pageSize = session.getServletContext().getInitParameter("pageSize");

            List<Book> bookList = bookService.selectByPriceLimit(Integer.parseInt(pageSize), Integer.parseInt(pageNum), new BigDecimal(priceBottom), new BigDecimal(priceTop));
            Page page = pageService.getPage(pageNum, pageSize, bookList);

            session.setAttribute("bookList", bookList);
            session.setAttribute("page", page);
            return "thymeleaf:index";
        } catch (Exception e) {
            TransactionUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            TransactionUtils.commit();
        }
    }

    public String toOrder(String pageNum, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "thymeleaf:index";
        }
        try {
            TransactionUtils.beginTransaction();
            if (pageNum == null) {
                pageNum = "1";
            }
            String pageSize = session.getServletContext().getInitParameter("pageSize");
            if (pageSize == null) {
                pageSize = "15";
            }

            List<Order> orderList = orderService.getOrderByUserAndLimit(user, Integer.parseInt(pageSize), Integer.parseInt(pageNum));
            Page page = pageService.getPage(pageNum, pageSize, orderList);

            session.setAttribute("orderPage", page);
            session.setAttribute("orderList", orderList);
            return "thymeleaf:order/order";
        } catch (Exception e) {
            TransactionUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            TransactionUtils.commit();
        }
    }

}
