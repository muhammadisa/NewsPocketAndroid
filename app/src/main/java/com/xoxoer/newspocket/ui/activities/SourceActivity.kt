package com.xoxoer.newspocket.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.xoxoer.newspocket.R
import com.xoxoer.newspocket.base.BaseAppCompatActivity
import com.xoxoer.newspocket.databinding.ActivityHomeBinding
import com.xoxoer.newspocket.databinding.ActivitySourceBinding
import com.xoxoer.newspocket.model.source.Source
import com.xoxoer.newspocket.ui.adapters.SourceAdapter
import com.xoxoer.newspocket.ui.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourceActivity : BaseAppCompatActivity() {

    @VisibleForTesting
    val newsViewModel by viewModels<NewsViewModel>()

    private val binding: ActivitySourceBinding by binding(R.layout.activity_source)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@SourceActivity
            vm = newsViewModel
            sourceAdapter = SourceAdapter()
            intent.getStringExtra("CATEGORY").also { category ->
                newsViewModel.category.set(category)
                newsViewModel.fetchSource()
            }
        }
    }
}