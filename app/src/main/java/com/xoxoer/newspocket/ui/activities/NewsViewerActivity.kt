package com.xoxoer.newspocket.ui.activities

import android.os.Bundle
import com.xoxoer.newspocket.R
import com.xoxoer.newspocket.base.BaseAppCompatActivity
import com.xoxoer.newspocket.databinding.ActivityNewsViewerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsViewerActivity : BaseAppCompatActivity() {

    private val binding: ActivityNewsViewerBinding by binding(R.layout.activity_news_viewer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@NewsViewerActivity
            article = intent.getParcelableExtra("ARTICLE")
        }
    }

}