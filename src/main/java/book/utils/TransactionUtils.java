package book.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: Ding
 * @date: 2022/6/29
 * @description: 事务控制工具类
 * @modify:
 */

public class TransactionUtils {

    private TransactionUtils() {}

    /**
     * 本低线程用于维护当前线程的 SqlSession
     */
    private static ThreadLocal<SqlSession> threadLocal;

    /**
     * 实例化一个 SqlSession 工厂
     */
    private static SqlSessionFactory sqlSessionFactory;

    static {
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

    /**
     * 开启事务
     */
    public static void beginTransaction() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession == null) {
            sqlSession = createSqlSession();
            threadLocal.set(sqlSession);
        } else {
            LoggerUtils.logInfo(1, "发现嵌套事务");
        }
    }

    /**
     *
     * @return 返回一条 SqlSession
     */
    public static SqlSession getSqlSession() {
        return getSqlSessionOrElse(true, "未开启事务 / 事务已关闭 的情况下就尝试获取 SqlSession");
    }

    /**
     *
     * @param type 要得到的 Mapper代理对象的类
     * @return 返回 Mapper 代理对象
     */
    public static <T> T getMapper(Class<T> type) {
        SqlSession sqlSession = getSqlSessionOrElse(true, "未开启事务 / 事务已关闭 的情况下就尝试获取 Mapper 代理对象");
        return sqlSession.getMapper(type);
    }

    /**
     * 得到一个 SqlSession ，若 {@code threadLocal.get()} 为 null 则新创建一个并打印错误日志
     * @param warnMessage 若 {@code threadLocal.get()} 为 null 时打印的 warn 日志
     * @return 返回一个可用的 SqlSession
     */
    private static SqlSession getSqlSessionOrElse(boolean isCreate, String warnMessage) {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession == null) {
            LoggerUtils.logWarn(2, warnMessage);
            if (isCreate) {
                sqlSession = createSqlSession();
                threadLocal.set(sqlSession);
            }
        }
        return sqlSession;
    }

    /**
     * 回滚并关闭 SqlSession
     */
    public static void rollback() {
        SqlSession sqlSession = getSqlSessionOrElse(false, "未开启事务 / 事务已关闭 的情况下就尝试 进行回滚操作");
        if (sqlSession != null) {
            sqlSession.rollback();
            sqlSession.close();
            threadLocal.set(null);
        }
    }

    /**
     * 提交并关闭 SqlSession
     */
    public static void commit() {
        SqlSession sqlSession = getSqlSessionOrElse(false, "未开启事务 / 事务已关闭 的情况下就尝试 进行提交操作");
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
            threadLocal.set(null);
        }
    }

    /**
     *
     * @return 返回一条新建的 SqlSession，并且开启手动事务
     */
    private static SqlSession createSqlSession() {
        return sqlSessionFactory.openSession(false);
    }
}
