package book.pojo;

import java.math.BigDecimal;

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
    private User user;

    /**
     * 总金额
     */
    private BigDecimal allPrice;

    public CartItem() {
    }

    public CartItem(Book book, Integer buyCount, User user, BigDecimal allPrice) {
        this.book = book;
        this.buyCount = buyCount;
        this.user = user;
        this.allPrice = allPrice;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(BigDecimal allPrice) {
        this.allPrice = allPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", book=" + book +
                ", buyCount=" + buyCount +
                ", user=" + user +
                ", allPrice=" + allPrice +
                '}';
    }
}
