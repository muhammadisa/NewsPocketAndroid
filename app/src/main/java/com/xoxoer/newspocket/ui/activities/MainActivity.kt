package com.xoxoer.newspocket.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.xoxoer.newspocket.R
import com.xoxoer.newspocket.base.BaseAppCompatActivity
import com.xoxoer.newspocket.databinding.ActivityMainBinding
import com.xoxoer.newspocket.ui.viewmodels.ExampleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseAppCompatActivity() {

    @VisibleForTesting
    val exampleViewModel by viewModels<ExampleViewModel>()

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exampleViewModel.fetchExample()

        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = exampleViewModel
            buttonRefresh.setOnClickListener {
                exampleViewModel.fetchExample()
            }
        }
    }

}