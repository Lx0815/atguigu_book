package book.controller;

import book.pojo.*;
import book.service.BookService;
import book.service.CartItemService;
import book.service.OrderItemService;
import book.service.OrderService;
import book.utils.TransactionUtils;

import javax.servlet.http.HttpSession;

/**
 * @author: Ding
 * @date: 2022/6/30
 * @description:
 * @modify:
 */

public class CartItemController {

    private CartItemService cartItemService;

    private BookService bookService;

    private OrderService orderService;

    private OrderItemService orderItemService;

    /**
     * 在首页进行添加购物车操作
     * @param bookId 要添加的书籍
     * @param session 会话域
     * @return 重新渲染主页
     */
    public String indexAddCart(String bookId, HttpSession session) {

        try {
            TransactionUtils.beginTransaction();
            User user = (User) session.getAttribute("user");
            cartItemService.addOneCartItem(bookId, user);

            return "thymeleaf:index";
        } catch (Exception e) {
            TransactionUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            TransactionUtils.commit();
        }
    }

    /**
     * 在购物车页面进行添加到购物车
     * @param id {@link CartItem#getId()}
     * @param newBuyCount 修改后的购物车数量
     * @param bookId 修改的图书信息
     * @param session 会话信息
     * @return 视图处理
     */
    public String updateCartItem(String id, String newBuyCount, String bookId, HttpSession session) {
        if (newBuyCount == null || bookId == null) {
            return "thymeleaf:user/login";
        }

        try {
            TransactionUtils.beginTransaction();

            cartItemService.doUpdateCartItem(id, newBuyCount, bookId);

            return "thymeleaf:cart/cart";
        } catch (Exception e) {
            TransactionUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            TransactionUtils.commit();
        }
    }



    public String pay(String cartItemIds, String allPrice, HttpSession session) {
        try {
            TransactionUtils.beginTransaction();

            Order order = orderService.createOrderByCartItem(cartItemIds, allPrice, session);

            session.setAttribute("payOrder", order);

            return "thymeleaf:cart/checkout";
        } catch (Exception e) {
            TransactionUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            TransactionUtils.commit();
        }
    }
}
