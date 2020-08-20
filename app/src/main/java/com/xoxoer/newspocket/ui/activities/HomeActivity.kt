package com.xoxoer.newspocket.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.xoxoer.newspocket.R
import com.xoxoer.newspocket.base.BaseAppCompatActivity
import com.xoxoer.newspocket.databinding.ActivityHomeBinding
import com.xoxoer.newspocket.extensions.expand
import com.xoxoer.newspocket.extensions.hide
import com.xoxoer.newspocket.extensions.isExpanded
import com.xoxoer.newspocket.extensions.transformToBottomSheetDialog
import com.xoxoer.newspocket.model.source.Source
import com.xoxoer.newspocket.route.InitialRouteName.HEADLINE
import com.xoxoer.newspocket.route.Route
import com.xoxoer.newspocket.ui.adapters.*
import com.xoxoer.newspocket.ui.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : BaseAppCompatActivity() {

    @VisibleForTesting
    val newsViewModel by viewModels<NewsViewModel>()

    private val binding: ActivityHomeBinding by binding(R.layout.activity_home)

    private lateinit var bottomSheetFilter: BottomSheetBehavior<LinearLayout>

    fun headlineFilter(v: View) {
        newsViewModel.clearSourceFilter()
        newsViewModel.filterMode.set(true)
        if (bottomSheetFilter.isExpanded()) {
            bottomSheetFilter.hide()
        } else {
            bottomSheetFilter.expand()
        }
    }

    fun sourceFilter(v: View) {
        newsViewModel.clearSourceFilter()
        newsViewModel.filterMode.set(false)
        if (bottomSheetFilter.isExpanded()) {
            bottomSheetFilter.hide()
        } else {
            bottomSheetFilter.expand()
        }
    }

    fun searchHeadline(v: View) {
        Route(v.context, HEADLINE).navigate(
            "SOURCE",
            Source(
                "", "",
                editTextSearchArticle.text.toString(),
                "", "", "Everywhere", ""
            )
        )
    }

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
            bottomSheetFilter = findViewById<LinearLayout>(R.id.bottom_sheet_filter)
                .transformToBottomSheetDialog(
                    isHideable = true,
                    onSliding = {},
                    onExpand = {},
                    onCollapse = {},
                    onDrag = {},
                    onSettle = {},
                    onHalfExpand = {},
                    onHide = {}
                )
        }
    }

}