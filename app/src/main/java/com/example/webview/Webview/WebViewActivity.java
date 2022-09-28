package com.example.webview.Webview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.webview.R;


public class WebViewActivity extends AppCompatActivity {
    //声明引用
    private WebView mWVmhtml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        //获取控件对象
        mWVmhtml=(WebView) findViewById(R.id.WVButton_Id);
        //加载本地html文件（存在assets文件夹）
        //mWVmhtml.loadUrl("file:///android_asset/ex4-09.html");
        //运行网页HTML
        //mWVmhtml.loadUrl("https://blog.csdn.net/qq_36243942/article/details/82187204");
        //设置支持JavaScript
        mWVmhtml.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        mWVmhtml.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        mWVmhtml.getSettings().setBuiltInZoomControls(true);
        //自适应屏幕
        mWVmhtml.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWVmhtml.getSettings().setLoadWithOverviewMode(true);
        //硬件加速
        mWVmhtml.setLayerType(WebView.LAYER_TYPE_HARDWARE, null);

        //访问网址的url
        //mWVmhtml.loadUrl("https://m.baidu.com");
        mWVmhtml.loadUrl("http://sso.nau.edu.cn/sso/login?service=http%3A%2F%2Fmy.nau.edu.cn%2F");

        //设置在当前WebView继续加载网页
        mWVmhtml.setWebViewClient(new MyWebViewClient());
        //继承WebChromeClient这个类，复写里面常用的方法，设置网页标题：
        mWVmhtml.setWebChromeClient(new MyWebChromeClient());
    }
    //新建一个类继承WebVIewClient 复写它的方法shouldOverrideUrlLoading,运行他在当前WebView继续加载网页
    class MyWebViewClient extends WebViewClient{
        @Override  //WebView代表是当前的WebView
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            //表示在当前的WebView继续打开网页
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d("WebView","开始访问网页");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("WebView","访问网页结束");
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //如果按返回键，此时WebView网页可以后退
        if (keyCode==KeyEvent.KEYCODE_BACK&&mWVmhtml.canGoBack()){
            mWVmhtml.goBack();//返回上一界面
            return true;
        }else {
            System.exit(0);//退出程序
        }
        return super.onKeyDown(keyCode, event);
    }
    class MyWebChromeClient extends WebChromeClient {
        @Override //监听加载进度
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }
        @Override//接受网页标题
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            //把当前的Title设置到Activity的title上显示
            setTitle(title);
        }
    }
}