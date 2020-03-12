package com.gameboxlink.videoadsdk.utils;

import android.content.Context;

public class ConfigHelper {
    private static ConfigHelper instance;

    public static ConfigHelper getInstance() {
        if (null == instance) {
            synchronized (ConfigHelper.class) {
                instance = new ConfigHelper();
            }
        }
        return instance;
    }

    /**
     * 保存当前的对象
     *
     * @param serverBean  实体
     */
    public void saveConfigInfo(Context context, String serverBean) {
        SPUtil.putParcelableObject(context,"ServerBean", serverBean);
    }

    public String getConfigInfo(Context context) {
        try {
            String serverBean = SPUtil.getParcelableObject(context,"ServerBean");
            return serverBean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void clear(Context context) {
        SPUtil.remove(context,"ServerBean");
    }



}
