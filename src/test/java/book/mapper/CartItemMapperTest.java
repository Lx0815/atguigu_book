package book.mapper;

import book.pojo.Book;
import book.pojo.CartItem;
import book.pojo.User;
import book.utils.TransactionUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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

    @Test
    void insertOne() {
        try {

            CartItemMapper mapper = TransactionUtils.getMapper(CartItemMapper.class);
            UserMapper userMapper = TransactionUtils.getMapper(UserMapper.class);

            User user = userMapper.selectById(1);
            Book book = new Book();
            book.setId(100);

            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setBook(book);
            cartItem.setAllPrice(BigDecimal.TEN);
            cartItem.setBuyCount(1);
            mapper.insertOne(cartItem);
        } catch (Exception e) {
            TransactionUtils.rollback();
        }
    }

    @Test
    void updateById() {
        CartItemMapper mapper = TransactionUtils.getMapper(CartItemMapper.class);

        UserMapper userMapper = TransactionUtils.getMapper(UserMapper.class);
        User user = userMapper.selectById(1);
        Book book = new Book();
        book.setId(200);

        CartItem cartItem = new CartItem(book, 2, user, BigDecimal.ZERO);
        cartItem.setId(21);

        mapper.updateById(cartItem);

    }
}