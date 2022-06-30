package book.mapper;

import book.pojo.CartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartItemMapper {
    List<CartItem> selectByUserId(@Param("userId") Integer userId);
}
