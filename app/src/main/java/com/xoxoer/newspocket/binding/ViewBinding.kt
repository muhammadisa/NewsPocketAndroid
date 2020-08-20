package com.xoxoer.newspocket.binding

import android.view.View
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xoxoer.newspocket.extensions.createDialog
import com.xoxoer.newspocket.extensions.gone
import com.xoxoer.newspocket.ui.viewmodels.NewsViewModel

@BindingAdapter("errorHandler", "errorReasonHandler")
fun bindError(
    view: View,
    error: ObservableField<Boolean>,
    errorReason: ObservableField<String>
) {
    when (error.get()) {
        true -> view
            .context.createDialog(
                "Alert",
                errorReason.get()!!
            ).show()

        false -> {
        }
    }
}

@BindingAdapter("toast")
fun bindToast(view: View, text: LiveData<String>) {
    if (!text.value.isNullOrEmpty()) {
        Toast.makeText(view.context, text.value, Toast.LENGTH_SHORT).show()
    }
}

@BindingAdapter("gone")
fun bindGone(view: View, isGone: Boolean) {
    view.gone(isGone)
}

@BindingAdapter("showWhileLoading")
fun bindShowWhileLoading(view: View, isLoading: MutableLiveData<Boolean>) {
    isLoading.observeForever {
        if (it) {
            view.gone(false)
        } else {
            view.gone(true)
        }
    }
}

@BindingAdapter("hideWhileLoading")
fun bindHideWhileLoading(view: View, isLoading: MutableLiveData<Boolean>) {
    isLoading.observeForever {
        if (it) {
            view.gone(true)
        } else {
            view.gone(false)
        }
    }
}