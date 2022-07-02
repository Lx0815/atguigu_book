package book.controller;

import book.pojo.*;
import book.service.BookService;
import book.service.CartItemService;
import book.service.OrderService;
import book.service.PageService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Enumeration;
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
            // 置空所有对象
            Enumeration<String> attributeNames = session.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String attributeName = attributeNames.nextElement();
                if ("bookList".equals(attributeName) || "page".equals(attributeName)) {
                    continue;
                }
                session.setAttribute(attributeName, null);
            }
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


        User user = (User) session.getAttribute("user");

        // 更新数据
        List<CartItem> cartItemList = cartItemService.getAllCartItemsByUser(user);
        session.setAttribute("cartItemList", cartItemList);
        return "thymeleaf:cart/cart";

    }

    public String toCheckout() {
        return "thymeleaf:cart/checkout";
    }

    public String filterByPrice(String pageNum, String priceBottom, String priceTop, HttpSession session) {
        if (priceBottom == null) priceBottom = "0";
        if (pageNum == null) pageNum = "1";
        if (priceTop == null) priceTop = "99999999999999";
        String pageSize = session.getServletContext().getInitParameter("pageSize");

        List<Book> bookList = bookService.selectByPriceLimit(Integer.parseInt(pageSize), Integer.parseInt(pageNum), new BigDecimal(priceBottom), new BigDecimal(priceTop));
        Page page = pageService.getPage(pageNum, pageSize, bookList);

        session.setAttribute("bookList", bookList);
        session.setAttribute("page", page);
        return "thymeleaf:index";

    }

    public String toOrder(String pageNum, HttpSession session) {

        User user = (User) session.getAttribute("user");
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

    }

    public String toManager() {
        return "thymeleaf:manager/manager";
    }

    public String toBookManager(HttpSession session) {
        List<Book> bookListManager = bookService.selectAll();
        session.setAttribute("bookListManager", bookListManager);
        return "thymeleaf:manager/book_manager";
    }

    public String toOrderManager(HttpSession session) {

        List<Order> allOrderList = orderService.getAllOrder();
        session.setAttribute("allOrderList", allOrderList);
        return "thymeleaf:manager/order_manager";

    }

    public String toBookEdit(String editBookId, HttpSession session) {
        if (editBookId != null) {
            Book editBook = bookService.selectById(Integer.parseInt(editBookId));
            session.setAttribute("editBook", editBook);
        }
        return "thymeleaf:manager/book_edit";
    }
}
