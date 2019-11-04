package com.shouzhong.imagecompress;

import android.app.Application;

class AppUtils {

    static Application getApp() {
        try {
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object thread = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(thread);
            if (app == null) throw new NullPointerException("error");
            return (Application) app;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

}
