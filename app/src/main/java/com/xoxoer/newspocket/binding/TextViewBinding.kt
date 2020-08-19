package com.xoxoer.newspocket.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.xoxoer.newspocket.extensions.toReadableLocalDateTime

@BindingAdapter("formatToReadable")
fun bindFormatToReadable(view: TextView, date: String){
    view.text = date.toReadableLocalDateTime("MMMM dd, yyyy HH:mm:ss")
}