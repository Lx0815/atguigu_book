package book.controller;

import book.pojo.Book;
import book.pojo.CartItem;
import book.pojo.User;
import book.service.BookService;
import book.service.CartItemService;
import book.utils.TransactionUtils;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * @author: Ding
 * @date: 2022/6/30
 * @description:
 * @modify:
 */

public class CartItemController {

    private CartItemService cartItemService;

    private BookService bookService;

    public String addInCart(String bookId, HttpSession session) {
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
}
