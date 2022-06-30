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
}
