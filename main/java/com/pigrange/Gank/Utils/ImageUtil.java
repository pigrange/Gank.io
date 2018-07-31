package com.pigrange.Gank.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil{
    public static Uri saveImage(Context context, String url,Bitmap bitmap, ImageView imageView,String tag){

        String imageDir = Environment.getExternalStorageDirectory().getPath()+"/Gank";
        String[] fileNameARR = url.substring(url.lastIndexOf("/") + 1).split("\\.");
//        String[] fileNameARR = url.substring(url.lastIndexOf("/"+1)).split("\\.");
        String fileName = fileNameARR[0] + ".png";

        File fileDir = new File(imageDir);
        if(!fileDir.exists()){
            fileDir.mkdir();
        }

        File imageFile = new File(fileDir,fileName);
        try{
            FileOutputStream fos = new FileOutputStream(imageFile);
            Boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
            if (tag.equals("save")){
                if (compress){
                    Toast.makeText(context,"保存成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"保存失败",Toast.LENGTH_SHORT).show();
                }
            }else {
                if (!compress){
                    Toast.makeText(context,"保存失败",Toast.LENGTH_SHORT).show();
                }
            }
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri uri = Uri.fromFile(imageFile);
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,uri));
        return uri;
    }
}
