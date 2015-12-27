package com.funny.note.mynote.app.utils;


public class MyLog {
    public static boolean isDebuging() {
        return DEBUG_MODE;
    }

    static boolean DEBUG_MODE = MyConfig.DEBUG_MODE;
    static String LOG_TAG = MyLog.class.getSimpleName();
    static long startTime = System.nanoTime();
    static String lastFileName = "";

    public static void line() {
        if (DEBUG_MODE) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final StackTraceElement ste = stack[1];
            String fileName = ste.getFileName();
            if (fileName.equalsIgnoreCase(lastFileName)) {
                fileName = getBlankString(lastFileName.length());
            } else {
                lastFileName = fileName;
            }
            android.util.Log.d(LOG_TAG, " ");
            android.util.Log.d(LOG_TAG, " ");
            android.util.Log.d(LOG_TAG, " ");
            android.util.Log.d(LOG_TAG, "ouyang");
            android.util.Log.d(LOG_TAG,
                    String.format("------------------------===========[%s][%s]%s==========----------------------------------", fileName, ste.getMethodName(), ste.getLineNumber()));
            android.util.Log.d(LOG_TAG, " ");
            android.util.Log.d(LOG_TAG, " ");
            android.util.Log.d(LOG_TAG, " ");
        }
    }

    public static void v(String info) {
        if (DEBUG_MODE) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final StackTraceElement ste = stack[1];
            String fileName = ste.getFileName();
            if (fileName.equalsIgnoreCase(lastFileName)) {
                fileName = getBlankString(lastFileName.length());
            } else {
                lastFileName = fileName;
            }
            android.util.Log.d(LOG_TAG, String.format("ouyang%s[%s]%s[%s]", fileName, ste.getMethodName(), ste.getLineNumber(), info));
        }
    }

    private static String getBlankString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void i(String info) {
        if (DEBUG_MODE) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            StackTraceElement ste = stack[1];
            String fileName = ste.getFileName();
            if (fileName.equalsIgnoreCase(lastFileName)) {
                fileName = getBlankString(lastFileName.length());
            } else {
                lastFileName = fileName;
            }
            android.util.Log.d(LOG_TAG, String.format("ouyang%s[%s]%s[%s]", fileName, ste.getMethodName(), ste.getLineNumber(), info));
            for (int i = 2; i <= (stack.length > 2 ? 2 : 1); i++) {
                ste = stack[i];
                fileName = getBlankString((lastFileName + "------ ").length()) + ste.getFileName();
                android.util.Log.d(LOG_TAG, String.format("%s[%s]%s", fileName, ste.getMethodName(), ste.getLineNumber()));
            }
        }
    }

    public static void d(String info) {
        if (DEBUG_MODE) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            StackTraceElement ste = stack[1];
            String fileName = ste.getFileName();
            if (fileName.equalsIgnoreCase(lastFileName)) {
                fileName = getBlankString(lastFileName.length());
            } else {
                lastFileName = fileName;
            }
            android.util.Log.d(LOG_TAG, String.format("ouyang%s[%s]%s[%s]", fileName, ste.getMethodName(), ste.getLineNumber(), info));
            for (int i = 2; i < stack.length; i++) {
                ste = stack[i];
                fileName = getBlankString((lastFileName + "------ ").length()) + ste.getFileName();
                android.util.Log.d(LOG_TAG, String.format("%s[%s]%s", fileName, ste.getMethodName(), ste.getLineNumber()));
            }
            android.util.Log.d(LOG_TAG, String.format("ouyangstack end!!--length:[%s]", stack.length - 1));
        }
    }

    public static void startTiming() {
        startTime = System.nanoTime();
        if (DEBUG_MODE) {
            StackTraceElement[] stack = new Throwable().getStackTrace();
            StackTraceElement ste = stack[1];
            String fileName = ste.getFileName();
            if (fileName.equalsIgnoreCase(lastFileName)) {
                fileName = getBlankString(lastFileName.length());
            } else {
                lastFileName = fileName;
            }
            android.util.Log.d(LOG_TAG, String.format("ouyang%s[%s]%s[%s]", fileName, ste.getMethodName(), ste.getLineNumber(), "StartTiming..."));
        }
    }

    public static float stopTiming() {
        long consumingTime = System.nanoTime() - startTime;
        float timeConsuming = (int) (consumingTime / 1000f / 1000f * 100) / 100f;
        if (DEBUG_MODE) {
            StackTraceElement[] stack = new Throwable().getStackTrace();
            StackTraceElement ste = stack[1];
            String fileName = ste.getFileName();
            if (fileName.equalsIgnoreCase(lastFileName)) {
                fileName = getBlankString(lastFileName.length());
            } else {
                lastFileName = fileName;
            }
            android.util.Log.d(LOG_TAG, String.format("ouyang%s[%s]%s Time-consuming %s Milliseconds", fileName, ste.getMethodName(), ste.getLineNumber(), timeConsuming));
        }
        return timeConsuming;
    }

    public static void e(String info) {
        if (DEBUG_MODE) {
            android.util.Log.e(LOG_TAG, info);
        }
    }

}
