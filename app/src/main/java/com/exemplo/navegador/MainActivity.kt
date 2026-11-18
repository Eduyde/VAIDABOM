package com.exemplo.navegador

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var barraTopo: LinearLayout
    private lateinit var etUrl: EditText
    private lateinit var btnIr: Button
    private lateinit var btnMostrarBarra: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        barraTopo = findViewById(R.id.barraTopo)
        etUrl = findViewById(R.id.etUrl)
        btnIr = findViewById(R.id.btnIr)
        btnMostrarBarra = findViewById(R.id.btnMostrarBarra)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                view.loadUrl(request.url.toString())
                return true
            }
        }

        btnIr.setOnClickListener {
            var url = etUrl.text.toString().trim()

            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://$url"
            }

            webView.loadUrl(url)
            barraTopo.visibility = android.view.View.GONE
        }

        btnMostrarBarra.setOnClickListener {
            barraTopo.visibility = android.view.View.VISIBLE
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) webView.goBack() else finish()
            }
        })
    }
}
