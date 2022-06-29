package book.pojo;

/**
 * @author: Ding
 * @date: 2022/6/29
 * @description: 购物车项
 * @modify:
 */

public class CartItem {
    /**
     * id
     */
    private Integer id;

    /**
     * 书
     */
    private Book book;

    /**
     * 买的数量
     */
    private Integer buyCount;

    /**
     * 用户 id
     */
    private int userId;

    public CartItem() {
    }

    public CartItem(Book book, Integer buyCount, int userId) {
        this.book = book;
        this.buyCount = buyCount;
        this.userId = userId;
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

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
