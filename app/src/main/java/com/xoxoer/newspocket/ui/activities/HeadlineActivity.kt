package com.xoxoer.newspocket.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.xoxoer.newspocket.R
import com.xoxoer.newspocket.base.BaseAppCompatActivity
import com.xoxoer.newspocket.databinding.ActivityHeadlineBinding
import com.xoxoer.newspocket.databinding.ActivityHomeBinding
import com.xoxoer.newspocket.model.source.Source
import com.xoxoer.newspocket.ui.adapters.HeadlineAdapter
import com.xoxoer.newspocket.ui.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeadlineActivity : BaseAppCompatActivity() {

    @VisibleForTesting
    val newsViewModel by viewModels<NewsViewModel>()

    private val binding: ActivityHeadlineBinding by binding(R.layout.activity_headline)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@HeadlineActivity
            vm = newsViewModel
            headlineAdapter = HeadlineAdapter()

            intent.getParcelableExtra<Source>("SOURCE").also { src ->
                source = src
                if(src?.id.isNullOrBlank()) {
                    newsViewModel.source.set(src)
                    newsViewModel.fetchEverythingByQuery()
                }else{
                    newsViewModel.source.set(src)
                    newsViewModel.fetchHeadlineBySource()
                }
            }
        }
    }
}