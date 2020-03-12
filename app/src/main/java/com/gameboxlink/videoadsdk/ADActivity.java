package com.gameboxlink.videoadsdk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.gameboxlink.videoadsdk.utils.LogUtilUpdate;


import java.io.File;

public class ADActivity extends AppCompatActivity {
    private static final int UPDATE_UI_SRC = 0x02;

    private static final String TAG = "ADActivity";
    private int scrWidth, scrHeight;

    private VideoView mVideoView;
    private ImageButton btnDownload;
    private View mainLayout;
    private LinearLayout btnArray;
    private ImageButton refreshBtn;
    private ImageView backgroundView;
    private Context mContext;
    private String downloadURL = "http://gameboxlink.com";
    private int selectButtonType = 0;
    private String videoPath = "https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/998c700eca44f3078e83bc3def237567.mp4";    //测试用
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private Intent intent;
    private ImageView iv_banner_1;
    private ImageView iv_banner_2;
    private ImageView iv_head_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = ADActivity.this;
        acWindow = (ADActivity) mContext;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lib_video_main_layout);
        intent = getIntent();
        initView();
    }

    private void initView() {
        String filePath = intent.getStringExtra("filePath");
        LogUtilUpdate.e(filePath + "/banner-1.png");
        Bitmap bitmap1 = BitmapFactory.decodeFile(filePath + "/banner-1.png");
        Bitmap bitmap2 = BitmapFactory.decodeFile(filePath + "/banner-2.png");
        Bitmap bitmap3 = BitmapFactory.decodeFile(filePath + "/title.png");
        iv_head_title = findViewById(R.id.iv_head_title);
        iv_head_title.setImageBitmap(bitmap3);
        iv_banner_1 = findViewById(R.id.iv_banner_1);
        iv_banner_1.setImageBitmap(bitmap1);
        iv_banner_2 = findViewById(R.id.iv_banner_2);
        iv_banner_2.setImageBitmap(bitmap2);

        mainLayout = findViewById(R.layout.lib_video_main_layout);
        btnDownload = (ImageButton) findViewById(R.id.lib_video_btn_download);
        backgroundView = (ImageView) findViewById(R.id.lib_video_backgroundView);
        mVideoView = (VideoView) findViewById(R.id.lib_video_video_view);
        scrWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        scrHeight = mContext.getResources().getDisplayMetrics().heightPixels;

    }


    public void reSetLayoutParams() {
        //对于全屏拉伸操作，主要就是如下代码
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mVideoView.setLayoutParams(layoutParams);

        ViewGroup.LayoutParams layoutParams2 = mVideoView.getLayoutParams();
        layoutParams2.width = scrWidth;
        layoutParams2.height = scrHeight;
        mVideoView.setLayoutParams(layoutParams2);
    }

    /**
     * 初始化视频播放器
     */
    private void initVideoObj() {
        mHandler.sendEmptyMessage(UPDATE_UI_SRC);
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //播放完成
            }
        });

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);    //  循环播放
            }
        });
    }


    public void onPause() {
        super.onPause();
        mVideoView.pause();
        mHandler.removeMessages(UPDATE_UI_SRC);
    }

    public static ADActivity acWindow = null;

    /**
     * 在别的Activity关闭自己的方法
     */
    public static void finishActivity() {
        if (acWindow != null && (!acWindow.isDestroyed())) {
            acWindow.finish();
            acWindow = null;
        }
    }

}
