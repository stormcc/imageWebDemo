package org.example.imagewebdemo.demos.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by xingmao on 2017/6/19.
 */
public final class LogExceptionStackUtil {

    public static String logExceptionStack(Throwable e) {
        StringWriter errorsWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(errorsWriter));
        return errorsWriter.toString();
    }


}
