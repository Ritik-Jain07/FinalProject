package com.example.finalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class WebActivity : AppCompatActivity() {
    var webview: WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        webview = findViewById(R.id.web_view)
        webview!!.webViewClient = MyWebViewClient()

        if (intent.hasExtra("url")) {

            if (intent.getStringExtra("url")!!.isNotBlank()) {
                webview!!.loadUrl(intent.getStringExtra("url")!!);

            }
        }
    }

    class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }
}