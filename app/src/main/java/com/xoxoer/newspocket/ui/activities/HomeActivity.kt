package com.xoxoer.newspocket.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.xoxoer.newspocket.R
import com.xoxoer.newspocket.base.BaseAppCompatActivity
import com.xoxoer.newspocket.databinding.ActivityHomeBinding
import com.xoxoer.newspocket.model.source.Source
import com.xoxoer.newspocket.route.InitialRouteName.HEADLINE
import com.xoxoer.newspocket.route.Route
import com.xoxoer.newspocket.ui.adapters.*
import com.xoxoer.newspocket.ui.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseAppCompatActivity() {

    @VisibleForTesting
    val newsViewModel by viewModels<NewsViewModel>()

    private val binding: ActivityHomeBinding by binding(R.layout.activity_home)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsViewModel.fetchHeadline()

        binding.apply {
            lifecycleOwner = this@HomeActivity
            vm = newsViewModel
            sourceAdapter = SourceAdapter()
            headlineAdapter = HeadlineAdapter()
            categoryAdapter = CategoryAdapter(newsViewModel)
            languageAdapter = LanguageAdapter(newsViewModel)
            countryAdapter = CountryAdapter(newsViewModel)

            imageViewSearch.setOnClickListener {
                Route(it.context, HEADLINE).navigate(
                    "SOURCE",
                    Source(
                        "",
                        "",
                        editTextSearchArticle.text.toString(),
                        "",
                        "",
                        "Everywhere",
                        ""
                    )
                )
            }
        }
    }

}