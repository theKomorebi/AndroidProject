package com.example.work;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button businessButton;
    private final String spName = "config";
    private final String username = "Username";
    private final String pwd = "Password";
    private final String rememberPwd = "IsRememberPwd";
    private final String autoLogin = "IsAutoLogin";
    private SharedPreferences sp;
    private EditText et_name;
    private EditText et_pwd;
    private CheckBox cb_remeberpwd;
    private CheckBox cb_autologin;
    private Button bt_login;
    private Button bt_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // getSP
//        sp = getSharedPreferences(spName, Context.MODE_PRIVATE);
//        initView();
//
//        // 回显数据
//        boolean rePwd = sp.getBoolean(rememberPwd, false);
//        boolean autologin = sp.getBoolean(autoLogin, false);
//
//        if (rePwd) {
//            String name = sp.getString(username, "");
//            String pwd = sp.getString("pwd", "");
//            et_name.setText(name);
//            et_pwd.setText(pwd);
//
//            cb_remeberpwd.setChecked(true);
//        }
//
//        if (autologin) {
//            cb_autologin.setChecked(true);
//
//            Toast.makeText(this, "已经自动登录了", Toast.LENGTH_SHORT).show();
//        }

    }

    /**
     * 初始化
     */

    private void initView() {
        // 找到控件
        et_name = findViewById(R.id.et_name);
        et_pwd = findViewById(R.id.et_pwd);
        cb_remeberpwd = findViewById(R.id.cb_remeberpwd);
        cb_autologin = findViewById(R.id.cb_autologin);
        bt_login = findViewById(R.id.bt_login);
        bt_register = findViewById(R.id.bt_register);

        // 设置监听
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        bt_login.setOnClickListener(myOnClickListener);
        bt_register.setOnClickListener(myOnClickListener);

    }

    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bt_login:
                    // 登录操作
                    String name = et_name.getText().toString().trim();
                    String password = et_pwd.getText().toString().trim();
                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
                        Toast.makeText(MainActivity.this, "用户名或密码无效", Toast.LENGTH_SHORT).show();
                    } else {
                        // 是否记住密码
                        if (cb_remeberpwd.isChecked()) {
                            // 用户名和密码需要保存，同时记住密码的状态也保存
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString(username, name);
                            editor.putString(pwd, password);
                            editor.putBoolean(rememberPwd, true);
                            editor.apply();
                        }

                        // 自动登录
                        if (cb_autologin.isChecked()) {
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putBoolean(autoLogin, true);
                            editor.apply();
                        }
                    }
                    break;
                case R.id.bt_register:
                    break;
            }
        }
    }

    public void clickToDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher_background)
                .setMessage("hello world")
                .setTitle("i am one")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNeutralButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()
                .show();
    }
}