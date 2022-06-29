package method_call_trace;

import book.utils.LoggerUtils;

/**
 * @author: Ding
 * @date: 2022/6/29
 * @description: 方法调用跟踪情况测试
 * @modify:
 */

public class MethodCallTrace01 {
    public static void main(String[] args) {
//        System.out.println(LoggerUtils.getMethodCalled());
        test01();
    }

    private static void test01() {
        Thread currentThread = Thread.currentThread();
        /*
          返回表示此线程的堆栈转储的 堆栈跟踪元素数组。
          如果此线程尚未启动、已启动但尚未被系统调度运行或已终止，则此方法将返回一个长度为零的数组。
          如果返回的数组长度非零，则数组的第一个元素表示堆栈的顶部，这是序列中最近的方法调用。

          数组的最后一个元素表示堆栈的底部，它是序列中最近的方法调用。
          返回值：一个StackTraceElement数组，每个代表一个栈帧。
          抛出：SecurityException – 如果存在安全管理器并且其 checkPermission 方法不允许获取线程的堆栈跟踪。
         */
        test02();
        StackTraceElement[] stackTrace = currentThread.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            System.out.println(stackTraceElement.toString());
        }
//        System.out.println(LoggerUtils.getMethodCalled());
    }

    private static void test02() {

    }
}
