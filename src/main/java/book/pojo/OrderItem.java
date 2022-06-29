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
    private Integer bookCount;

    /**
     * 所属订单
     */
    private Order orderId;

    public OrderItem() {
    }

    public OrderItem(Book book, Integer bookCount, Order orderId) {
        this.book = book;
        this.bookCount = bookCount;
        this.orderId = orderId;
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
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }
}
