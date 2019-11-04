package com.shouzhong.imagecompress;

import android.graphics.BitmapFactory;
import android.text.TextUtils;

import java.io.File;
import java.util.Random;

public class ImageCompressUtils {

    public static String compress(String filePath) {
        return compress(filePath, null, 100, false);
    }

    /**
     * 图片压缩，请在子线程运行
     *
     * @param filePath 原始图片路径
     * @param targetDir 压缩后图片保存的目录，不传为当前app目录的files/image
     * @param leastCompressSize 不压缩的阈值，单位为K
     * @param focusAlpha 是否保留透明通道
     * @return 压缩后的图片路径
     */
    public static String compress(String filePath, String targetDir, int leastCompressSize, boolean focusAlpha) {
        if (TextUtils.isEmpty(filePath) || filePath.endsWith(".gif")) return null;
        try {
            File file = new File(filePath);
            if (!file.exists() || file.isDirectory()) return null;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            if (options.outWidth == -1) return null;
        } catch (Exception e) {
            return null;
        }
        if (!Checker.SINGLE.needCompress(leastCompressSize, filePath)) return filePath;
        InputStreamProvider input = null;
        try {
            input = new InputStreamProvider(filePath);
            String suffix = Checker.SINGLE.extSuffix(input);
            if (TextUtils.isEmpty(suffix)) suffix = ".jpg";
            String dir = TextUtils.isEmpty(targetDir) ? AppUtils.getApp().getExternalFilesDir("image").getAbsolutePath() : targetDir;
            if (!dir.endsWith("/")) dir += "/";
            String targetPath = dir + "image" + System.currentTimeMillis() + (new Random().nextInt(8999) + 1000) + suffix;
            return new Engine(new InputStreamProvider(filePath), new File(targetPath), focusAlpha).compress().getAbsolutePath();
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (input != null) input.close();
            } catch (Exception e) { }
        }
    }
}
