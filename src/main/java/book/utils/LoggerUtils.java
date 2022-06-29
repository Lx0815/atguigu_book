package book.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Ding
 * @date: 2022/6/29
 * @description: Logger 工具类
 * @modify:
 */

public class LoggerUtils {

    private LoggerUtils() {}

    /**
     * 打印 info 级别的日志
     * @param loggerName 自定义日志记录器名称
     * @param message 日志信息
     */
    public static void logInfo(String loggerName, String message) {
        Logger logger = LoggerFactory.getLogger(loggerName);
        logger.info(message);
    }

    /**
     * 打印 info 级别的日志。此日志记录器的名称由日志记录位置命名
     * @param stackLevelOffset 堆栈层数偏移，如想要使日志记录器名称为 <b>{@code LoggerUtils.logInfo()} 所在方法的调用者</b> 时，就应在栈帧中更深入一层，此时参数应为 1
     * @param message 日志信息
     */
    public static void logInfo(int stackLevelOffset, String message) {
        Logger logger = LoggerFactory.getLogger(getMethodCalled(3 + stackLevelOffset));
        logger.info(message);
    }

    /**
     * 打印 info 级别的日志。此日志记录器的名称由日志记录位置命名
     * @param message 日志信息
     */
    public static void logInfo(String message) {
        logInfo(0, message);
    }

    /**
     * 打印 warn 级别的日志
     * @param stackLevelOffset 堆栈层数偏移，详见 {@link #logInfo(int, String)}
     * @param message 打印的参数信息
     */
    public static void logWarn(int stackLevelOffset, String message) {
        Logger logger = LoggerFactory.getLogger(getMethodCalled(3 + stackLevelOffset));
        logger.warn(message);
    }

    /**
     * 打印 warn 级别的日志。此日志记录器的名称由日志记录位置命名
     * @param message 日志信息
     */
    public static void logWarn(String message) {
        logWarn(0, message);
    }

    /**
     * 在这里我们把 {@code getStackTrace()} 返回的包含栈帧的数组按照索引分层。
     * 本方法位于 第 1 层， 第 0 层为：{@code java.lang.Thread.getStackTrace(Thread.java:1559)}
     *
     * @param stackLevel 层级
     * @return 返回栈桢中的第 {@code stackLevel} 层
     */
    public static String getMethodCalled(int stackLevel) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int len = stackTrace.length;
        if (len < stackLevel) {
            return stackTrace[len - 1].toString();
        }
        return stackTrace[stackLevel].toString();
    }

    /**
     *
     * @return 一个字符串表示当前方法的位置
     */
    public static String getMethodCalled() {
        return getMethodCalled(2);
    }
}
