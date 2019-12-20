package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wulianwang.lsp.R;
import com.wulianwang.lsp.bean.User;
import com.wulianwang.lsp.util.SharedPrefsUtil;

public class MainActivity extends AppCompatActivity {

    private Button im1, im2, im3, im4;
    boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //电力服务
        im1 = findViewById(R.id.imageView);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ElectricityServiceActivity.class);
                startActivity(intent);
            }
        });
        //任务广场
        im2 = findViewById(R.id.imageView3);
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllServiceActivity.class);
                startActivity(intent);
            }
        });
        //人员列表
        im3 = findViewById(R.id.imageView5);
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PeopleListActivity.class);
                startActivity(intent);
            }
        });
        //我的、登录
        im4 = findViewById(R.id.imageView2);
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLogin){
                    Intent intent = new Intent(MainActivity.this, MyInfoActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        User user = SharedPrefsUtil.getValue(this, "user", (User)null);

        if(user == null){
            isLogin = false;
            im4.setText("登录/注册");
        }else{
            isLogin = true;
            im4.setText("我的");
        }
    }
}
