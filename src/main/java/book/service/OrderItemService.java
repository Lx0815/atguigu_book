package book.service;

import book.mapper.OrderItemMapper;
import book.pojo.Book;
import book.pojo.CartItem;
import book.pojo.Order;
import book.pojo.OrderItem;
import book.utils.LoggerUtils;
import book.utils.TransactionUtils;

/**
 * @author: Ding
 * @date: 2022/7/1
 * @description:
 * @modify:
 */

public class OrderItemService {

    private CartItemService cartItemService;

    private BookService bookService;

    public void insertByArr(OrderItem[] orderItems) {
        OrderItemMapper mapper = TransactionUtils.getMapper(OrderItemMapper.class);
        mapper.insertByArr(orderItems);
    }

    public OrderItem[] createOrderItemByArr(String[] cartItemStrArr, Order order) {
        // 生成订单详情
        OrderItem[] orderItems = new OrderItem[cartItemStrArr.length];
        CartItem cartItem;
        Book book;
        for (int i = 0; i < cartItemStrArr.length; i++) {
            // 跳过无效 id
            if ("".equals(cartItemStrArr[i])) continue;
            // 获取单个地购物车项
            cartItem = cartItemService.getCartItemById(Integer.parseInt(cartItemStrArr[i]));
            // 校验库存是否充足
            Integer remainingStock = bookService.selectRemainingStockByCartItemId(cartItem.getId());
            if (remainingStock < 0) {
                // 可以细化到具体是什么商品库存不足。此过程或许需要优化返回值为 枚举类型，或普通的对象，总之得封装返回值
                return null;
            }
            // 更新库存
            book = cartItem.getBook();
            book.setId(cartItem.getBook().getId());
            book.setBookCount(remainingStock);
            book.setSaleCount(book.getSaleCount() + cartItem.getBuyCount());
            book.setBookImg(null);
            book.setBookName(null);
            book.setBookStatus(null);
            book.setAuthor(null);
            book.setPrice(null);
            bookService.updateById(book);

            // 这里只需要 book 的 id，所以无需担心用于更新库存的 book 无法用于新建订单详情
            orderItems[i] = new OrderItem(book, cartItem.getBuyCount(), order);
            LoggerUtils.logInfo("orderItem["+ i +"] : " + orderItems[i].toString());
        }
        return orderItems;
    }
}
