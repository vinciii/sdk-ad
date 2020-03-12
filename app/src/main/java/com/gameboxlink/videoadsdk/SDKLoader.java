package com.gameboxlink.videoadsdk;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import com.gameboxlink.videoadsdk.utils.ConfigHelper;
import com.gameboxlink.videoadsdk.utils.ConstantSDK;
import com.gameboxlink.videoadsdk.utils.JsonUtils;
import com.gameboxlink.videoadsdk.utils.LogUtilUpdate;
import com.gameboxlink.videoadsdk.utils.ThreadPoolUtils;
import com.gameboxlink.videoadsdk.utils.ZipUtils;

import org.json.JSONObject;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;


public class SDKLoader {

    public static final String TOP_DIR_NAME = "data/data/com.example.sdkdemo/VideoCacheFlash";


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    private static SDKLoader instance;
    private Context mContext;
    AdInteractionListener listener;

    public static SDKLoader getInstance() {
        if (instance == null) {
            instance = new SDKLoader();
        }
        return instance;
    }

    /**
     * 初始化
     *
     * @param mContext
     * @param adInteractionListener
     */
    public void init(Context mContext, AdInteractionListener adInteractionListener) {
        this.mContext = mContext;
        this.listener = adInteractionListener;
        //权限
        boolean b = verifyStoragePermissions((Activity) mContext);
        if (b) {
            //先从服务器获取Json配置数据
            ThreadPoolUtils.execute(new Runnable() {
                @Override
                public void run() {
                    getConfigJsonFromServer();
                }
            });
        }
    }


    private void getConfigJsonFromServer() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(ConstantSDK.INIT_URL).openConnection();
            connection.setRequestMethod("GET");
            //连接
            connection.setConnectTimeout(30 * 1000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                String s = JsonUtils.readMyInputStream(connection.getInputStream());
                ArrayanalysisJson(s);
                LogUtilUpdate.e(s);
            } else {
                ThreadPoolUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listener.onConfigStatus(false, "配置文件下载失败");
                    }
                });
                LogUtilUpdate.e("onFailure: ");
                getConfigJsonFromServer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ArrayanalysisJson(final String json) {
        ConfigHelper.getInstance().saveConfigInfo(mContext, json);
        ThreadPoolUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listener.onConfigStatus(true, json);
            }
        });
        getZipFromServer();
    }


    private void getZipFromServer() {
        final String path = getTopDirPath();

        try {
            JSONObject jsonObject = new JSONObject(ConfigHelper.getInstance().getConfigInfo(mContext));
            HttpURLConnection connection = (HttpURLConnection) new URL(jsonObject.getString("BaseResUrl")).openConnection();
            connection.setRequestMethod("GET");
            //连接
            connection.setConnectTimeout(60 * 1000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                File file = ZipUtils.saveFile(path, "uibase.zip", connection.getInputStream());
                ZipUtils.deleteAllFiles(new File(getTopDirPath() + "/uibase"));
                File file1 = ZipUtils.UnZipFolder(file.getAbsolutePath(), getTopDirPath());
                if (file1 != null) {
                    ThreadPoolUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.onLoadUiBase(true, getTopDirPath() + "/uibase");
                        }
                    });
                }
            } else {
                ThreadPoolUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listener.onLoadUiBase(false, getTopDirPath());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 在对sd卡进行读写操作之前调用这个方法
     * Checks if the app has permission to write to device storage
     * If the app does not has permission then the user will be prompted to grant permissions
     */
    public static boolean verifyStoragePermissions(Activity activity) {
        int permission1 = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission1 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
        return permission1 == PackageManager.PERMISSION_GRANTED;
    }


    public static String getTopDirPath() {
        String dir_path = getSDCardPath() + File.separator + TOP_DIR_NAME;
        return dir_path;
    }


    private static String getSDCardPath() {
        return Environment.getExternalStorageDirectory() + "";
    }
}
