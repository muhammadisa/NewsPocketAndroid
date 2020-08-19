package com.xoxoer.newspocket.ui.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData

interface ViewModelContracts {
    var valid: ObservableBoolean
    val isLoading: MutableLiveData<Boolean>
    val error: ObservableField<Boolean>
    val errorReason: ObservableField<String>
}