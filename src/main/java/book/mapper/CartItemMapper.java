package book.mapper;

import book.pojo.CartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartItemMapper {
    List<CartItem> selectByUserId(@Param("userId") Integer userId);

    CartItem selectByUserIdAndBookId(@Param("userId") Integer userId,
                                     @Param("bookId") Integer bookId);

    void insertOne(CartItem cartItem);

    void updateById(CartItem cartItem);
}
