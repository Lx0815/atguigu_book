package book.mapper;

import book.pojo.User;
import book.utils.TransactionUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserMapperTest {

    private UserMapper mapper;

    @Test
    void selectByUsernameAndPassword() {
        System.out.println(mapper.selectByUsernameAndPassword("001", "123"));
    }

    @Test
    void insertOneByUser() {
        User user = new User("001", "123", "123@123.com", 0);
        mapper.insertOneByUser(user);
    }

    @BeforeEach
    void setUp() {
        TransactionUtils.beginTransaction();
        mapper = TransactionUtils.getMapper(UserMapper.class);
    }

    @AfterEach
    void tearDown() {
        TransactionUtils.commit();
    }
}