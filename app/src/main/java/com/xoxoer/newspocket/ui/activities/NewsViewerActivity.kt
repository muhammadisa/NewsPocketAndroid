package com.xoxoer.newspocket.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xoxoer.newspocket.R
import com.xoxoer.newspocket.base.BaseAppCompatActivity
import com.xoxoer.newspocket.databinding.ActivityHomeBinding
import com.xoxoer.newspocket.databinding.ActivityNewsViewerBinding
import com.xoxoer.newspocket.model.headline.Article
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