package book.controller;

import book.pojo.Book;
import book.pojo.CartItem;
import book.pojo.User;
import book.service.BookService;
import book.service.CartItemService;
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

    public String toIndex(HttpSession session) {
        try {
            TransactionUtils.beginTransaction();
            List<Book> bookList = bookService.selectAll();
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
        return null;
    }

}
