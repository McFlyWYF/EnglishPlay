package com.example.englishplay.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.englishplay.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 小飞侠 on 2018/5/4.
 */

public class UpdatePwdActivity extends AppCompatActivity {

    private Intent intent;
    private EditText et_old_pwd, et_new_pwd;
    private Button cancelbtn,surebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_layout);
        findViews();
    }

    private void findViews() {
        et_old_pwd = (EditText) findViewById(R.id.input_password_old);
        et_new_pwd = (EditText) findViewById(R.id.input_password_new);

        cancelbtn = (Button) findViewById(R.id.btn_cancel);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdatePwdActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        surebtn = (Button) findViewById(R.id.btn_sure);
        surebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUsersPassword();
            }
        });
    }

    private void updateUsersPassword() {
        String oldPwd = et_old_pwd.getText().toString();
        String newPwd = et_new_pwd.getText().toString();
        BmobUser.updateCurrentUserPassword(oldPwd, newPwd, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(UpdatePwdActivity.this, "密码修改成功，可以用新密码进行登录啦", Toast.LENGTH_SHORT).show();
                    intent = new Intent(UpdatePwdActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(UpdatePwdActivity.this, "失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
