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
    private Integer bookCount;

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
    private Integer saleCount;

    /**
     * 图书状态
     */
    private Integer bookStatus;

    public Book() {}

    public Book(int bookCount, String bookName, BigDecimal price, String bookImg, String author, Integer saleCount, Integer bookStatus) {
        this.bookCount = bookCount;
        this.bookName = bookName;
        this.price = price;
        this.bookImg = bookImg;
        this.author = author;
        this.saleCount = saleCount;
        this.bookStatus = bookStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
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

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookCount=" + bookCount +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", bookImg='" + bookImg + '\'' +
                ", author='" + author + '\'' +
                ", saleCount=" + saleCount +
                ", bookStatus=" + bookStatus +
                '}';
    }
}
