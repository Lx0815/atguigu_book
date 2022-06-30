package book.mapper;

import book.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     * 根据 username 和 password 查询用户，多用于处理登录请求
     * @param username 用户名
     * @param password 用户密码
     * @return 返回查询到的用户 ，若查询不到则为 null
     */
    User selectByUsernameAndPassword(@Param("username") String username,
                                     @Param("password") String password);


    Integer insertOneByUser(User user);


    User selectById(@Param("id") Integer id);

}
