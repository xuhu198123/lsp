package com.wulianwang.lsp.util;

/**
 * Created by Administrator on 2018/1/3.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.wulianwang.lsp.bean.User;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * SharedPreferences存储数据方式工具类
 * @author zuolongsnail
 */
public class SharedPrefsUtil {
    public final static String SETTING = "Setting";
    public static void putValue(Context context, String key, int value) {
        SharedPreferences.Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putInt(key, value);
        sp.commit();
    }
    public static void putValue(Context context, String key, boolean value) {
        SharedPreferences.Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putBoolean(key, value);
        sp.commit();
    }
    public static void putValue(Context context, String key, String value) {
        SharedPreferences.Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putString(key, value);
        sp.commit();
    }
    public static int getValue(Context context, String key, int defValue) {
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        int value = sp.getInt(key, defValue);
        return value;
    }
    public static boolean getValue(Context context, String key, boolean defValue) {
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        boolean value = sp.getBoolean(key, defValue);
        return value;
    }
    public static String getValue(Context context, String key, String defValue) {
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String value = sp.getString(key, defValue);
        return value;
    }

    public static void putValue(Context context, String key, User value)  {
        if(value instanceof Serializable) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(SETTING, context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(value);//把对象写到流里
                String temp = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
                editor.putString(key, temp);
                editor.commit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                throw new Exception("User must implements Serializable");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static User getValue(Context context, String key, User defValue) {
        SharedPreferences sharedPreferences=context.getSharedPreferences(SETTING,context.MODE_PRIVATE);
        String temp = sharedPreferences.getString(key, "");
        ByteArrayInputStream bais =  new ByteArrayInputStream(Base64.decode(temp.getBytes(), Base64.DEFAULT));
        User user = defValue;
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            user = (User) ois.readObject();
        } catch (IOException e) {
        }catch(ClassNotFoundException e1) {

        }
        return user;
    }
}

