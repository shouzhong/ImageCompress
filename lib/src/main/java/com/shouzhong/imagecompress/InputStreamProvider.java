package com.shouzhong.imagecompress;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 通过此接口获取输入流，以兼容文件、FileProvider方式获取到的图片
 * <p>
 * Get the input stream through this interface, and obtain the picture using compatible files and FileProvider
 */
class InputStreamProvider {

    private InputStream inputStream;
    private String filePath;

    InputStreamProvider(String filePath) {
        this.filePath = filePath;
    }

    InputStream open() throws Exception {
        close();
        inputStream = new FileInputStream(filePath);
        return inputStream;
    }

    void close() {
        if (inputStream == null) return;
        try {
            inputStream.close();
        } catch (Exception ignore) {
        } finally {
            inputStream = null;
        }
    }

    String getPath() {
        return filePath;
    }
}
