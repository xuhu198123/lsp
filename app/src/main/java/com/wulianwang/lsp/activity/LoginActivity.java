package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wulianwang.lsp.R;
import com.wulianwang.lsp.bean.User;
import com.wulianwang.lsp.util.HttpUtil;
import com.wulianwang.lsp.util.SharedPrefsUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 *  任务：1.2 登录
 *
 *  成员：孔祥浩，李镕岩
 */


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn = (Button)this.findViewById(R.id.button3);
        TextView tv=(TextView)this.findViewById(R.id.textView22);
        TextView tv2=(TextView)this.findViewById(R.id.textView23);
        EditText editText1 = findViewById(R.id.editText1);
        EditText editText2 = findViewById(R.id.editText2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://123.7.17.91:7777/login";
                Map<String, String> map = new HashMap<>();
                map.put("phone", editText1.getText().toString().trim());
                map.put("password", editText2.getText().toString().trim());
                HttpUtil.post(url, null, new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                  //      Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                   //     Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                        String responseData = response.body().string();
                        Log.d("Login", responseData);
                        try {
                            JSONObject jsonObject = new JSONObject(responseData);
                            if(jsonObject.getInt("code") == 100){
                                JSONObject jsonUser = jsonObject.getJSONObject("extend").getJSONObject("user");
                                User user = new User();
                                user.setUserName(jsonUser.getString("username"));
                                user.setPassword(jsonUser.getString("password"));
                                user.setPhone(jsonUser.getString("phone"));
                                user.setEmail(jsonUser.getString("email"));
                                user.setNickName(jsonUser.getString("nickname"));
                                user.setSex(jsonUser.getString("sex"));
                                user.setAge(jsonUser.getInt("age"));
                                user.setHeadImg(jsonUser.getString("headImg"));
                                user.setProfession(jsonUser.getString("profession"));
                                user.setRealName(jsonUser.getString("realName"));
                                user.setRealEnterprise(jsonUser.getString("realEnterprise"));
                                user.setRealElectro(jsonUser.getString("realElectro"));
                                user.setRealOccupation(jsonUser.getString("realOccupation"));
                                user.setBeginTime(jsonUser.getString("beginTime"));
                                user.setUpdateTime(jsonUser.getString("updateTime"));

                                SharedPrefsUtil.putValue(LoginActivity.this, "user", user);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, null, map);

            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);

            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
