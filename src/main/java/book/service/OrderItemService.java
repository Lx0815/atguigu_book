package book.service;

import book.mapper.OrderItemMapper;
import book.pojo.OrderItem;
import book.utils.TransactionUtils;

/**
 * @author: Ding
 * @date: 2022/7/1
 * @description:
 * @modify:
 */

public class OrderItemService {
    public void insertByArr(OrderItem[] orderItems) {
        OrderItemMapper mapper = TransactionUtils.getMapper(OrderItemMapper.class);
        mapper.insertByArr(orderItems);
    }
}
