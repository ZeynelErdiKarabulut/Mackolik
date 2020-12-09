package com.zeynelerdi.mackolik.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.zeynelerdi.mackolik.R
import com.zeynelerdi.mackolik.databinding.FragmentNewsBinding
import com.zeynelerdi.mackolik.ui.browser.BrowserActivity
import com.zeynelerdi.mackolik.ui.browser.BrowserActivity.ARG.ARG_URL
import com.zeynelerdi.mackolik.ui.common.base.view.BaseFragment
import com.zeynelerdi.mackolik.ui.news.model.response.News
import javax.inject.Inject

class NewsFragment : BaseFragment(), NewsNavigator{

    private lateinit var mNewsFragmentBinding: FragmentNewsBinding

    @Inject
    lateinit var mNewsViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mNewsFragmentBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_news,
                container,
                false
            )
        mNewsViewModel.navigator = this
        mNewsFragmentBinding.lifecycleOwner = this
        mNewsFragmentBinding.viewModel = mNewsViewModel
        return mNewsFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNewsViewModel.getNews()
    }

    override fun onNewsClick(news: News) {
        val intent: Intent = Intent(activity, BrowserActivity::class.java)
        intent.putExtra(ARG_URL, news.link)
        startActivity(intent)
    }
}