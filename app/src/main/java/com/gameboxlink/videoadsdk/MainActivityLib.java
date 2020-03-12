package com.gameboxlink.videoadsdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gameboxlink.videoadsdk.utils.LogUtilUpdate;

public class MainActivityLib extends AppCompatActivity {

    private static final String TAG = "MainActivityLib";
    private Context mContext;
    private static boolean uiSucc = false;
    private static String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = this;

        final TextView viewById1 = findViewById(R.id.tv1);
        final TextView viewById2 = findViewById(R.id.tv2);

        SDKLoader.getInstance().init(mContext, new AdInteractionListener() {

            @Override
            public void onConfigStatus(boolean succ, String msg) {
                if (succ) {
                    Toast.makeText(mContext, "配置文件下载成功", 0).show();
                    viewById1.setText(msg);
                }
            }

            @Override
            public void onLoadUiBase(boolean succ, String filePath1) {
                if (succ) {
                    uiSucc = succ;
                    filePath = filePath1;
                    Toast.makeText(mContext, "uibase资源下载成功", 0).show();
                    viewById2.setText(filePath);
                }
            }
        });


        //打开广告
        findViewById(R.id.open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uiSucc) {
                    Intent intent = new Intent(MainActivityLib.this, ADActivity.class);
                    intent.putExtra("filePath", filePath);
                    startActivity(intent);
                }else {
                    Toast.makeText(mContext, "尚未完成初始化,请稍候", 0).show();
                }
            }
        });

        //关闭广告
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        //重新下载
        findViewById(R.id.restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uiSucc = false;
                SDKLoader.getInstance().init(mContext, new AdInteractionListener() {

                    @Override
                    public void onConfigStatus(boolean succ, String msg) {
                        Toast.makeText(mContext, msg, 0).show();
                    }

                    @Override
                    public void onLoadUiBase(boolean succ, String filePath) {
                        if (succ) {
                            uiSucc = succ;
                            filePath = filePath;
                            Toast.makeText(mContext, "uibase资源下载成功", 0).show();
                        }
                    }
                });

            }
        });
    }

    @Override
    protected void onDestroy() {
        //销毁广告
        super.onDestroy();
    }
}
