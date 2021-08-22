package com.example.mysolelife.contentsList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import com.example.mysolelife.R

class ContentShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_show)

        // ContentListActivity에서 넘긴 url 변수에 저장
        val getUrl = intent.getStringExtra("url")
        // Toast.makeText(this, getUrl, Toast.LENGTH_SHORT).show()

        // 가져온 url 웹뷰에 띄우기
        val webView : WebView = findViewById(R.id.webView)
        webView.loadUrl(getUrl.toString())
    }
}