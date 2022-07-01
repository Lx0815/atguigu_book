package book.service;

import book.mapper.CartItemMapper;
import book.pojo.Book;
import book.pojo.CartItem;
import book.pojo.User;
import book.utils.TransactionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Ding
 * @date: 2022/6/30
 * @description:
 * @modify:
 */

public class CartItemService {

    private BookService bookService;

    /**
     *
     * @param user 用户对象
     * @return 返回 user 用户的所有购物车项
     */
    public List<CartItem> getAllCartItemsByUser(User user) {
        CartItemMapper mapper = TransactionUtils.getMapper(CartItemMapper.class);
        return mapper.selectByUserId(user.getId());
    }

    public CartItem getCartItemByUserAndBook(User user, Book book) {
        CartItemMapper mapper = TransactionUtils.getMapper(CartItemMapper.class);
        return mapper.selectByUserIdAndBookId(user.getId(), book.getId());
    }

    public void insertOne(CartItem cartItem) {
        CartItemMapper mapper = TransactionUtils.getMapper(CartItemMapper.class);
        mapper.insertOne(cartItem);
    }

    public void updateById(CartItem cartItem) {
        CartItemMapper mapper = TransactionUtils.getMapper(CartItemMapper.class);
        mapper.updateById(cartItem);
    }

    public void deleteByIds(Integer[] cartItemIdsArr) {
        CartItemMapper mapper = TransactionUtils.getMapper(CartItemMapper.class);
        mapper.deleteByIds(cartItemIdsArr);
    }

    public CartItem getCartItemById(int id) {
        CartItemMapper mapper = TransactionUtils.getMapper(CartItemMapper.class);
        return mapper.selectById(id);
    }

    public void addOneCartItem(String bookId, User user) {
        Book book = bookService.selectById(Integer.parseInt(bookId));
        CartItem cartItem = this.getCartItemByUserAndBook(user, book);
        if (cartItem == null) {
            cartItem = new CartItem(book, 1, user, book.getPrice());
            // 更新购物车记录
            this.insertOne(cartItem);
        } else {
            cartItem.setBuyCount(cartItem.getBuyCount() + 1);
            // 更新购物车记录
            BigDecimal newAllPrice = cartItem.getBook().getPrice().multiply(new BigDecimal(cartItem.getBuyCount()));
            cartItem.setAllPrice(newAllPrice);
            cartItem.setBook(null);
            cartItem.setUser(null);
            this.updateById(cartItem);
        }
    }

    public void doUpdateCartItem(String id, String newBuyCount, String bookId) {
        // 创建一个新的 CartItem 用于传参
        CartItem cartItem = new CartItem();
        // 设置 id 用于在数据库中定位
        cartItem.setId(Integer.parseInt(id));
        // 设置新的购买数量
        cartItem.setBuyCount(Integer.parseInt(newBuyCount));
        // 根据 id 查询 book 价格
        BigDecimal bookPrice = bookService.selectPriceById(Integer.parseInt(bookId));

        cartItem.setAllPrice(bookPrice.multiply(new BigDecimal(cartItem.getBuyCount())));

        this.updateById(cartItem);
    }
}
