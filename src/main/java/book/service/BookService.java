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
    
    private BookMapper mapper = TransactionUtils.getMapper(BookMapper.class);

    /**
     *
     * @return 返回所有图书
     */
    public List<Book> selectAll() {
        
        return mapper.selectAll();
    }

    public List<Book> selectAllByLimit(int pageSize, int pageNum) {
        
        return mapper.selectByLimit((pageNum - 1) * pageSize, pageSize);
    }

    public Integer selectAllCount() {
        
        return mapper.selectAllCount();
    }

    public Book selectById(int bookId) {
        
        return mapper.selectById(bookId);
    }

    public BigDecimal selectPriceById(int id) {
        
        return mapper.selectPriceById(id);
    }

    public Integer selectRemainingStockByCartItemId(Integer cartItemId) {
        
        return mapper.selectRemainingStockByCartItemId(cartItemId);
    }

    public void updateById(Book book) {
        
        mapper.updateById(book);
    }

    public List<Book> selectByPriceLimit(Integer pageSize, Integer pageNum, BigDecimal priceBottom, BigDecimal priceTop) {
        
        return mapper.selectByPriceLimit((pageNum - 1) * pageSize, pageSize, priceBottom, priceTop);
    }

    public void deleteOne(int bookId) {
        
        mapper.deleteById(bookId);
    }

    public void insertOne(Book newBook) {
        
        mapper.insertOne(newBook);
    }
}
