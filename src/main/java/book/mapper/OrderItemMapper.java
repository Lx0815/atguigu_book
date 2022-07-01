package book.mapper;

import book.pojo.OrderItem;

public interface OrderItemMapper {
    void insertByArr(OrderItem[] orderItemArr);
}
