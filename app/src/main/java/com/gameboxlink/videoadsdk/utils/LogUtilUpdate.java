package com.gameboxlink.videoadsdk.utils;

import android.util.Log;

public final class LogUtilUpdate {

    /**
     * 日志开关
     */
    private static boolean b;

    static {
        b = true;//DownloadManager.getInstance().getConfiguration().isEnableLog();
    }

    /**
     * 输出Error信息
     *
     * @param tag tag
     * @param msg String
     */
    public static void e(String msg) {
        if (b) {
             e("AD_SDK", msg);
        }
    }
    /**
     * 输出Error信息
     *
     * @param tag tag
     * @param msg String
     */
    public static void e(String tag,String msg) {
        if (b) {
            Log.e("AD_SDK", msg);
        }
    }


    /**
     * 输出Debug信息
     *
     * @param tag tag
     * @param msg String
     */
    public static void d(String tag, String msg) {
        if (b) {
            Log.d(tag, msg);
        }
    }

    /**
     * 输出Info信息
     *
     * @param tag tag
     * @param msg 字符串
     */
    public static void i(String tag, String msg) {
        if (b) {
            Log.i(tag, msg);
        }
    }
}
