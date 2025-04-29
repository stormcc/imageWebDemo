package org.example.imagewebdemo.demos.util;


import com.google.common.base.Strings;

public class RuntimeFailureHolder {
    private static final InheritableThreadLocal<String> contextHolder = new InheritableThreadLocal<>();

    /**
     * 设置
     * @param failure
     */
    public static void setFailure(String failure) {
        contextHolder.set(failure);
    }
    /**
     * 获取
     * @return
     */
    public static String getFailure() {
        return contextHolder.get();
    }
    /**
     * 重置
     */
    public static void clearFailure() {
        contextHolder.remove();
    }
    /**
     * 判断是否包含
     * @return
     */
    public static boolean containFailure() {
        return !Strings.isNullOrEmpty(contextHolder.get());
    }
}
