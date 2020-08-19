package com.xoxoer.newspocket.extensions

import android.graphics.Bitmap
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import timber.log.Timber

fun WebView.loadWebView(url: String) {

    this.settings?.run {
        loadsImagesAutomatically = true
        domStorageEnabled = true
        setSupportZoom(true)
    }

    this.webViewClient = object : WebViewClient() {
        override fun onReceivedSslError(
            view: WebView?, handler: SslErrorHandler?, error: SslError?
        ) {
            handler?.proceed()
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url!!)
            return true
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            Timber.e("WEB_VIEW On Page Start")
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            Timber.e("WEB_VIEW On Page Finish")
        }

    }

    this.loadUrl(url)
}