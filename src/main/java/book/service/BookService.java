package book.service;

import book.mapper.BookMapper;
import book.pojo.Book;
import book.utils.TransactionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Ding
 * @date: 2022/6/30
 * @description:
 * @modify:
 */

public class BookService {

    /**
     *
     * @return 返回所有图书
     */
    public List<Book> selectAll() {
        BookMapper bookMapper = TransactionUtils.getMapper(BookMapper.class);
        return bookMapper.selectAll();
    }

    public List<Book> selectAllByLimit(int pageSize, int pageNum) {
        BookMapper mapper = TransactionUtils.getMapper(BookMapper.class);
        return mapper.selectByLimit((pageNum - 1) * pageSize, pageSize);
    }

    public Integer selectAllCount() {
        BookMapper mapper = TransactionUtils.getMapper(BookMapper.class);
        return mapper.selectAllCount();
    }

    public Book selectById(int bookId) {
        BookMapper mapper = TransactionUtils.getMapper(BookMapper.class);
        return mapper.selectById(bookId);
    }

    public BigDecimal selectPriceById(int id) {
        BookMapper mapper = TransactionUtils.getMapper(BookMapper.class);
        return mapper.selectPriceById(id);
    }

    public Integer selectRemainingStockByCartItemId(Integer cartItemId) {
        BookMapper mapper = TransactionUtils.getMapper(BookMapper.class);
        return mapper.selectRemainingStockByCartItemId(cartItemId);
    }

    public void updateById(Book book) {
        BookMapper mapper = TransactionUtils.getMapper(BookMapper.class);
        mapper.updateById(book);
    }

    public List<Book> selectByPriceLimit(Integer pageSize, Integer pageNum, BigDecimal priceBottom, BigDecimal priceTop) {
        BookMapper mapper = TransactionUtils.getMapper(BookMapper.class);
        return mapper.selectByPriceLimit((pageNum - 1) * pageSize, pageSize, priceBottom, priceTop);
    }
}
