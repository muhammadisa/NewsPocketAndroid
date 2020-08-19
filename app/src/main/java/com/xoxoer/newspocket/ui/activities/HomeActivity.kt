package com.xoxoer.newspocket.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.xoxoer.newspocket.R
import com.xoxoer.newspocket.base.BaseAppCompatActivity
import com.xoxoer.newspocket.databinding.ActivityHomeBinding
import com.xoxoer.newspocket.ui.adapters.SourceAdapter
import com.xoxoer.newspocket.ui.viewmodels.example.ExampleViewModel
import com.xoxoer.newspocket.ui.viewmodels.source.SourceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseAppCompatActivity() {

    @VisibleForTesting
    val sourceViewModel by viewModels<SourceViewModel>()

    private val binding: ActivityHomeBinding by binding(R.layout.activity_home)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        sourceViewModel.fetchSource()

        binding.apply {
            lifecycleOwner = this@HomeActivity
            vm = sourceViewModel
            adapter = SourceAdapter(sourceViewModel)
        }
    }

}