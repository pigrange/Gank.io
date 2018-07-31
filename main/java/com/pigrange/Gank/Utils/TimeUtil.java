package com.pigrange.Gank.Utils;

import android.provider.ContactsContract;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static String parseTime(String publish) {
        String[] ss = publish.split("");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : ss) {
            if (s.equals("T")) {
                break;
            } else {
                stringBuilder.append(s);
            }
        }
        return stringBuilder.toString();
    }
    public static int getCurrentDate(){
        if (DataHolder.data!=null){
            return Integer.valueOf(DataHolder.data);
        }else {
            DataHolder.data = new SimpleDateFormat("yyyyMMdd").format(new Date());
            return Integer.valueOf(DataHolder.data);
        }
    }

    private static class DataHolder{
        private static String data = null;
    }
}
