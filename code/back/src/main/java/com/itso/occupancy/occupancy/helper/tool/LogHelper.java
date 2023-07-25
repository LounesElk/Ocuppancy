package com.itso.occupancy.occupancy.helper.tool;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogHelper {

    public static String getStackTraceException(Exception exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        return sw.toString();
    }
}
