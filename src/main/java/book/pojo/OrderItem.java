package book.pojo;

/**
 * @author: Ding
 * @date: 2022/6/29
 * @description: orderItem
 * @modify:
 */

public class OrderItem {
    /**
     * 订单详情
     */
    private Integer id;

    /**
     * 订单中交易的书
     */
    private Book book;

    /**
     * 订单中交易的书的数量
     */
    private Integer buyCount;

    /**
     * 所属订单
     */
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Book book, Integer bookCount, Order order) {
        this.book = book;
        this.buyCount = bookCount;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBookCount() {
        return buyCount;
    }

    public void setBookCount(Integer bookCount) {
        this.buyCount = bookCount;
    }

    public Order getOrderId() {
        return order;
    }

    public void setOrderId(Order orderId) {
        this.order = orderId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", book=" + book +
                ", buyCount=" + buyCount +
                ", order=" + order +
                '}';
    }
}
