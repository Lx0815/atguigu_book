package book.pojo;

import java.math.BigDecimal;

/**
 * @author: Ding
 * @date: 2022/6/29
 * @description: book 实体类
 * @modify:
 */

public class Book {
    /**
     * id
     */
    private Integer id;

    /**
     * 库存
     */
    private int bookCount;

    /**
     * 书名
     */
    private String bookName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 书籍图片路径
     */
    private String bookImg;

    /**
     * 作者
     */
    private String author;

    /**
     * 销量
     */
    private int saleCount;

    public Book() {}

    public Book(int bookCount, String bookName, BigDecimal price, String bookImg, String author, int saleCount) {
        this.bookCount = bookCount;
        this.bookName = bookName;
        this.price = price;
        this.bookImg = bookImg;
        this.author = author;
        this.saleCount = saleCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }
}
