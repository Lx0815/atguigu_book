package book.controller;

import book.pojo.Book;
import book.pojo.CartItem;
import book.pojo.User;
import book.service.BookService;
import book.service.CartItemService;
import book.utils.LoggerUtils;
import book.utils.TransactionUtils;
import com.sun.corba.se.impl.naming.cosnaming.TransientNameServer;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author: Ding
 * @date: 2022/6/30
 * @description:
 * @modify:
 */

public class CartItemController {

    private CartItemService cartItemService;

    private BookService bookService;

    public String indexAddCart(String bookId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 说明用户未登录
            return "thymeleaf:user/login";
        }

        try {

            TransactionUtils.beginTransaction();
            Book book = bookService.selectById(Integer.parseInt(bookId));
            CartItem cartItem = cartItemService.getCartItemByUserAndBook(user, book);
            if (cartItem == null) {
                cartItem = new CartItem(book, 1, user, book.getPrice());
                // 更新购物车记录
                cartItemService.insertOne(cartItem);
//                return "script:<script type=\"text/javascript\">\n" +
//                        "        alert(\"添加成功\")\n" +
//                        "    </script>";
            } else {
                cartItem.setBuyCount(cartItem.getBuyCount() + 1);
                // 更新购物车记录
                BigDecimal newAllPrice = cartItem.getBook().getPrice().multiply(new BigDecimal(cartItem.getBuyCount()));
                cartItem.setAllPrice(newAllPrice);
                cartItem.setBook(null);
                cartItem.setUser(null);
                cartItemService.updateById(cartItem);
//                return "script:<script type=\"text/javascript\">\n" +
////                        "        alert(\"添加成功\")\n" +
////                        "    </script>";
            }
        } catch (Exception e) {
            TransactionUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            TransactionUtils.commit();
        }
        return "thymeleaf:index";
    }

    public String cartAddCart(String id, String newBuyCount, String bookId, HttpSession session) {
        if (newBuyCount == null || bookId == null || session.getAttribute("user") == null) {
            return "thymeleaf:user/login";
        }

        try {
            TransactionUtils.beginTransaction();
            CartItem cartItem = new CartItem();
            cartItem.setId(Integer.parseInt(id));
            cartItem.setBuyCount(Integer.parseInt(newBuyCount));
            Book book = bookService.selectById(Integer.parseInt(bookId));
            cartItem.setUser(((User) (session.getAttribute("user"))));
            cartItem.setAllPrice(book.getPrice().multiply(new BigDecimal(cartItem.getBuyCount())));
            cartItemService.updateById(cartItem);
            return "thymeleaf:cart/cart";
        } catch (Exception e) {
            TransactionUtils.rollback();
            throw new RuntimeException(e);
        } finally {
            TransactionUtils.commit();
        }
    }

    public String pay(String cartItemIds, String allPrice, HttpSession session) {
        // 处理 cartItemIds
        String[] cartItemStrArr = cartItemIds.split(",");
        // 批量删除
        Integer[] cartItemIdsArr = Arrays.stream(cartItemStrArr).map(Integer::parseInt).toArray(value -> new Integer[0]);
        cartItemService.deleteByIds(cartItemIdsArr);
    }
}
