package book.mapper;

import book.pojo.Book;

import java.util.List;

public interface BookMapper {
    List<Book> selectAll();
}
