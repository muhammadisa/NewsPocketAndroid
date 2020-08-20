package com.xoxoer.newspocket.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import com.xoxoer.newspocket.extensions.gone
import com.xoxoer.newspocket.extensions.toReadableLocalDateTime

@BindingAdapter("formatToReadable")
fun bindFormatToReadable(view: TextView, date: String) {
    view.text = date.toReadableLocalDateTime("MMMM dd, yyyy HH:mm:ss")
}

@BindingAdapter("hideWhenValueNull")
fun bindHideWhenValueNull(view: TextView, text: ObservableField<String>){
    if(text.get().isNullOrBlank()){
        view.gone(true)
    }else{
        view.text = text.get()
        view.gone(false)
    }
}