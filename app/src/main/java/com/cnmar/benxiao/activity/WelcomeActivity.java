package com.cnmar.benxiao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.cnmar.benxiao.R;
import com.cnmar.benxiao.utils.CheckNetwork;
import com.cnmar.benxiao.utils.SPHelper;

public class WelcomeActivity extends AppCompatActivity {
   private Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           if (msg.what==0){
 //            如果有网络，并且auto_login=true（登录用户勾选了自动登录），那么下次进入app直接跳转到主页面
               boolean auto_login = SPHelper.getBoolean(WelcomeActivity.this, "auto_login", false);
               if (CheckNetwork.isNetworkConnected(WelcomeActivity.this)) {
                   if (auto_login == false) {
                       Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                       startActivity(intent);
                   } else {
                       Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                       startActivity(intent);
                   }
               } else {
                   Toast.makeText(WelcomeActivity.this, R.string.check_network, Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                   startActivity(intent);
               }
           }
       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        启动页显示的背景图跟其主题的 windowBackground一样的时候，不用调用setContentView
//        setContentView(R.layout.activity_welcome);
        handler.sendEmptyMessageDelayed(0,1500);
    }
}
