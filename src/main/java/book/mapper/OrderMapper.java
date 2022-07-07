package book.mapper;

import book.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    void insertOne(Order order);

    /**
     * 根据 userId 查询用户所有的订单。需要注意的是此方法返回的对象中未进行对 orderUser 、orderItemList 属性的封装。
     * @param userId 用户 id
     * @param startRow 起始行数
     * @param pageSize 页面长度
     * @return 返回一个 List 集合
     */
    List<Order> selectByUserIdAndLimit(@Param("userId") Integer userId,
                                       @Param("startRow") Integer startRow,
                                       @Param("pageSize") Integer pageSize);

    List<Order> selectAll();
}
