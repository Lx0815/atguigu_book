package book.mapper;

import book.utils.TransactionUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderMapperTest {

    @BeforeEach
    void setUp() {
        TransactionUtils.beginTransaction();
    }

    @AfterEach
    void tearDown() {
        TransactionUtils.commit();
    }

    @Test
    void insertOne() {
    }

    @Test
    void selectByUserId() {
        OrderMapper mapper = TransactionUtils.getMapper(OrderMapper.class);
        System.out.println(mapper.selectByUserIdAndLimit(1, 0, 2));
    }
}