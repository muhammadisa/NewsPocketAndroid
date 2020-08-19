package com.xoxoer.newspocket.binding

import android.webkit.WebView
import androidx.databinding.BindingAdapter
import com.xoxoer.newspocket.extensions.loadWebView

@BindingAdapter("wvUrl")
fun bindWVUrl(view: WebView, url: String) {
    view.loadWebView(url)
}