package com.gameboxlink.videoadsdk.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadPoolUtils {
    private static Executor executor = Executors.newSingleThreadExecutor();
    private static Handler handler = new Handler(Looper.getMainLooper());
    public static void execute(Runnable runnable) {
        executor.execute(runnable);
    }
    public static void runOnUiThread(Runnable runnable) {
        handler.post(runnable);
    }
}