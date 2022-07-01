package book.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class TransactionUtilsTest {

    public static void main(String[] args) {
        test01();
    }

    static void test01() {
        SqlSessionFactory sqlSessionFactory;
        ThreadLocal<SqlSession> threadLocal;
        try {
            // 1. 获取 SqlSessionFactory
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            threadLocal = new ThreadLocal<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}