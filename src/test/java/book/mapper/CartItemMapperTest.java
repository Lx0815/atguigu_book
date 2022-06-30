package book.mapper;

import book.pojo.CartItem;
import book.utils.TransactionUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartItemMapperTest {

    @BeforeEach
    void setUp() {
        TransactionUtils.beginTransaction();
    }

    @AfterEach
    void tearDown() {
        TransactionUtils.commit();
    }

    @Test
    void selectByUserId() {
        CartItemMapper mapper = TransactionUtils.getMapper(CartItemMapper.class);
        List<CartItem> cartItemList = mapper.selectByUserId(1);
        System.out.println(cartItemList);
    }
}