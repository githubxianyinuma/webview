package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.webview.Webview.WebViewActivity;

public class MainActivity extends AppCompatActivity {
    //声明引用
    private Button mWVButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件对象
        mWVButton=(Button) findViewById(R.id.WVButton_Id);
        setlistener();
        //init();
    }
    //设置监听器
    public void setlistener(){
        ButtonClickListener listener =new ButtonClickListener();
        mWVButton.setOnClickListener(listener);
    }
    //实现监听器接口
    class ButtonClickListener implements OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.WVButton_Id:
                    intent =new Intent(MainActivity.this, WebViewActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
