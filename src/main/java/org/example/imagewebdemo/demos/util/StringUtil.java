package org.example.imagewebdemo.demos.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Create By: Jimmy Song
 * Create At: 2023-05-11 16:34
 */
public class StringUtil {
    private StringUtil(){}

    public static List<String> uniqList(List<String> list){
        if (list == null || list.isEmpty() ) {
            return new ArrayList<>();
        }
        LinkedHashSet<String> set = new LinkedHashSet<>(list);
        return new ArrayList<>(set);
    }

    public static String prefix(String s, int prefixNumber){
        if (prefixNumber<0) {
            throw new IllegalArgumentException("n不能小于等于0, prefixNumber:"+prefixNumber);
        }
        if (s == null || s.length() <prefixNumber ) {
            return s;
        }
        return s.substring(0, prefixNumber);
    }
}
