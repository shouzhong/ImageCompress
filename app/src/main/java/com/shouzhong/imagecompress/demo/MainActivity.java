package com.shouzhong.imagecompress.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.shouzhong.imagecompress.ImageCompressUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickTest(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String filePath = getExternalFilesDir("image").getAbsolutePath() + "/a.jpg";
                String targetPath = ImageCompressUtils.compress(filePath);
                Log.e("=======", "result=" + targetPath);
            }
        }).start();
    }
}
