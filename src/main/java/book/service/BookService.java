package book.service;

import book.mapper.BookMapper;
import book.pojo.Book;
import book.utils.TransactionUtils;

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
}
