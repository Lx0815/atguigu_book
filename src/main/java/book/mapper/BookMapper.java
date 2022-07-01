package book.mapper;

import book.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BookMapper {
    List<Book> selectAll();

    Book selectById(@Param("id") Integer id);

    List<Book> selectByLimit(@Param("pageStartRow") int pageStartRow,
                             @Param("pageSize") int pageSize);

    Integer selectAllCount();

    BigDecimal selectPriceById(@Param("id") Integer id);

    Integer selectRemainingStockByCartItemId(@Param("cartItemId") Integer cartItemId);

    void updateById(Book book);

    List<Book> selectByPriceLimit(@Param("pageStartRow") Integer pageStartRow,
                                  @Param("pageSize") Integer pageSize,
                                  @Param("priceBottom") BigDecimal priceBottom,
                                  @Param("priceTop") BigDecimal priceTop);
}
