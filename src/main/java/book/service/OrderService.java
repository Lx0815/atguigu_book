package book.service;

import book.mapper.OrderMapper;
import book.pojo.*;
import book.utils.LoggerUtils;
import book.utils.TransactionUtils;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Ding
 * @date: 2022/7/1
 * @description:
 * @modify:
 */

public class OrderService {

    private OrderItemService orderItemService;

    private CartItemService cartItemService;

    private BookService bookService;

    public void insertOne(Order order) {
        OrderMapper mapper = TransactionUtils.getMapper(OrderMapper.class);
        mapper.insertOne(order);
    }

    public Order createOrderByCartItem(String cartItemIds, String allPrice, HttpSession session) {
        // 处理 cartItemIds
        String[] cartItemStrArr = cartItemIds.split(",");
        LoggerUtils.logInfo("cartItemStrArr : " + Arrays.toString(cartItemStrArr));

        // 生成订单
        Order order = new Order(DigestUtils.md5Hex(cartItemIds + System.currentTimeMillis()), LocalDateTime.now(), ((User) session.getAttribute("user")),
                new BigDecimal(allPrice), 1);
        this.insertOne(order);

        // 生成订单详情
        OrderItem[] orderItems = orderItemService.createOrderItemByArr(cartItemStrArr, order);
        if (orderItems == null) throw new RuntimeException("订单详情生成失败");

        // 批量插入
        orderItemService.insertByArr(orderItems);

        // 批量删除
//        Integer[] cartItemIdsArr = Arrays.stream(cartItemStrArr).filter(s -> !"".equals(s)).map(Integer::parseInt).toArray(value -> new Integer[1]);
        Integer[] cartItemIdsArr = new Integer[cartItemStrArr.length];
        for (int i = 0; i < cartItemStrArr.length; i++) {
            if (! "".equals(cartItemStrArr[i])) {
                cartItemIdsArr[i] = Integer.parseInt(cartItemStrArr[i]);
            }
        }
        LoggerUtils.logInfo("cartItemIdsArr : " + Arrays.toString(cartItemIdsArr));
        cartItemService.deleteByIds(cartItemIdsArr);
        return order;
    }


    public List<Order> getOrderByUserAndLimit(User user, Integer pageSize, Integer pageNum) {
        OrderMapper mapper = TransactionUtils.getMapper(OrderMapper.class);
        return mapper.selectByUserIdAndLimit(user.getId(), (pageNum - 1) * pageSize, pageSize);
    }
}
