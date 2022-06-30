package book.service;

import book.mapper.CartItemMapper;
import book.pojo.CartItem;
import book.pojo.User;
import book.utils.TransactionUtils;

import java.util.List;

/**
 * @author: Ding
 * @date: 2022/6/30
 * @description:
 * @modify:
 */

public class CartItemService {

    /**
     *
     * @param user 用户对象
     * @return 返回 user 用户的所有购物车项
     */
    public List<CartItem> getAllCartItemsByUser(User user) {
        CartItemMapper mapper = TransactionUtils.getMapper(CartItemMapper.class);
        return mapper.selectByUserId(user.getId());
    }
}
