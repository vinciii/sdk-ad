package com.gameboxlink.videoadsdk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Base64;

import java.util.Map;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class SPUtil {

    private static String adsdk_preferences = "adsdk_preferences";

    private SPUtil() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }


    public static SharedPreferences getSP(Context context) {
        return context.getSharedPreferences(adsdk_preferences, Context.MODE_PRIVATE);
    }

    public static <T> void putParcelableObject(Context context,String key, T object) {
        if (object == null) {
            put(context,key, null);
            return;
        }
        String json= (String) object;
        if (json == null) {
            return;
        }
        try {
            //将序列化的数据用Base64加密
            String base64Str = Base64.encodeToString(json.getBytes("utf-8"), Base64.NO_PADDING);
            //保存该16进制数组
            put(context,key, base64Str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static  String getParcelableObject(Context context,String key) {
        try {
            if (getSP(context).contains(key)) {
                String string = (String) get(context,key, "");
                string = new String(Base64.decode(string.getBytes("utf-8"), Base64.NO_PADDING), "utf-8");
                return  string;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public static void put(Context context,String key, Object object) {
        SharedPreferences.Editor editor = getSP(context).edit();

        if (object == null) {
            editor.putString(key, null);
        } else if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else if (object != null) {
            editor.putString(key, object.toString());
        }

        editor.apply();
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(Context context,String key, @NonNull Object defaultObject) {
        SharedPreferences sp = getSP(context);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        } else {
            throw new RuntimeException("The default value defaultObject not be null,you must define Class type");
        }
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(Context context,String key) {
        SharedPreferences sp = getSP(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 清除所有数据
     */
    public static void clear(Context context) {
        SharedPreferences sp = getSP(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public static boolean contains(Context context,String key) {
        SharedPreferences sp = getSP(context);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = getSP(context);
        return sp.getAll();
    }
}