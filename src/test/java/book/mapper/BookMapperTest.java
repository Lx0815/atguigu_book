package book.mapper;

import book.pojo.Book;
import book.utils.TransactionUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {

    @BeforeEach
    void setUp() {
        TransactionUtils.beginTransaction();
    }

    @AfterEach
    void tearDown() {
        TransactionUtils.commit();
    }

    @Test
    void selectAll() {
        BookMapper mapper = TransactionUtils.getMapper(BookMapper.class);
        List<Book> bookList = mapper.selectAll();
        bookList.forEach(System.out::println);
    }

    @Test
    void selectById() {
    }

    @Test
    void selectByLimit() {
        BookMapper mapper = TransactionUtils.getMapper(BookMapper.class);
        System.out.println(mapper.selectByLimit(0, 15));
    }

    @Test
    void updateById() {

    }
}