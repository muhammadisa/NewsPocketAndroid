package com.xoxoer.newspocket.binding

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import com.xoxoer.newspocket.ui.viewmodels.NewsViewModel

@BindingAdapter("editKeywordOnVM")
fun bindOnTextChangedEditText(view: EditText, vm: NewsViewModel){
    view.addTextChangedListener {
        vm.keyword.set(it.toString())
    }
}