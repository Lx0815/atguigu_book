package book.mapper;

import book.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    List<Book> selectAll();

    Book selectById(@Param("id") Integer id);
}
