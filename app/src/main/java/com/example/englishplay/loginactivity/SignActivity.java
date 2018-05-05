package com.example.englishplay.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishplay.R;
import com.example.englishplay.bean.User;

import javax.security.auth.login.LoginException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 小飞侠 on 2018/5/4.
 */

public class SignActivity extends AppCompatActivity {


    private EditText mUsername = null;
    private EditText mPassword = null;
    private Button msignbtn = null;
    private TextView mlogintv = null;

    @Override
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.sign_layout);
        findViews();
    }

    private void findViews() {
        mUsername = (EditText) findViewById(R.id.input_account_sign);
        mPassword = (EditText) findViewById(R.id.input_password_sign);
        msignbtn = (Button) findViewById(R.id.sign_btn);
        mlogintv = (TextView) findViewById(R.id.tv_login);

        msignbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commit();
            }
        });

        mlogintv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void commit(){
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        BmobUser user = new BmobUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null){
                    Toast.makeText(SignActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(SignActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                    Log.e("TAG","e");
                }
            }
        });
    }
}
