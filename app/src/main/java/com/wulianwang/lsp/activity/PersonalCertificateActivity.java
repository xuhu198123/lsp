package com.wulianwang.lsp.activity;




/**
 * 许浒 李鹏 5.3
 */



import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import android.widget.ImageButton;
import android.widget.ImageView;

import com.wulianwang.lsp.R;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;


public class PersonalCertificateActivity extends AppCompatActivity {
    static final int TAKE_PHOTO=1;
    static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS=1;
    ImageButton imbt1,imbt2,imbt3,imbtbak;
    int i=0;

    Uri[] uris = new Uri[3];
    String[] names = new String[]{"image1.jpg", "image2.jpg", "image3.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_certificate);



        imbt1 =(ImageButton)findViewById(R.id.imageButton);
        imbt2 =(ImageButton)findViewById(R.id.imageButton2);
        imbt3 =(ImageButton)findViewById(R.id.imageButton3);
        imbtbak=(ImageButton)findViewById(R.id.imageButton6);


        imbtbak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=0;
                requestPermission();
            }
        });


        imbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=1;
                requestPermission();
            }
        });


        imbt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=2;
                requestPermission();
            }
        });

    }


    public void takePhoto(int  i)
    {
        File outputImage = new File(getExternalCacheDir(), names[i]);
        try//判断图片是否存在，存在则删除在创建，不存在则直接创建
        {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24)
        {    uris[i] = FileProvider.getUriForFile(PersonalCertificateActivity.this,
                "com.example.cameraalbumtest.fileprovider", outputImage);
        }
        else{
            uris[i]=Uri.fromFile(outputImage);
        }
        Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uris[i]);

        startActivityForResult(intent,TAKE_PHOTO);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK)
                {
                    try {
                        switch (i){
                            case 0:
                                Bitmap bitmap1= BitmapFactory.decodeStream(getContentResolver().openInputStream(uris[i]));
                                imbt1.setBackground(new BitmapDrawable(bitmap1));
                                break;
                            case 1:
                                Bitmap bitmap2= BitmapFactory.decodeStream(getContentResolver().openInputStream(uris[i]));
                                imbt2.setBackground(new BitmapDrawable(bitmap2));
                                break;
                            case 2:
                                Bitmap bitmap3= BitmapFactory.decodeStream(getContentResolver().openInputStream(uris[i]));
                                imbt3.setBackground(new BitmapDrawable(bitmap3));
                                break;
                        }
                    }
                    catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }



    private void requestPermission()
    {


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);

            }

        }else{
            takePhoto(i);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePhoto(i);
                }
                else {
                    showWaringDialog();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void showWaringDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("警告！")
                .setMessage("请前往设置->应用->PermissionDemo->权限中打开相关权限，否则功能无法正常运行！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 一般情况下如果用户不授权的话，功能是无法运行的，做退出处理
                        finish();
                    }
                }).show();
    }
}


