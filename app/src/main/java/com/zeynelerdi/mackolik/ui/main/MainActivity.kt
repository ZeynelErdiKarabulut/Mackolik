package com.zeynelerdi.mackolik.ui.main

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.zeynelerdi.mackolik.R
import com.zeynelerdi.mackolik.databinding.ActivityMainBinding
import com.zeynelerdi.mackolik.ui.common.base.view.BaseFragmentActivity
import com.zeynelerdi.mackolik.ui.common.components.spinner.MackolikSpinner
import com.zeynelerdi.mackolik.ui.common.components.spinner.OnSpinnerItemSelectedListener
import com.zeynelerdi.mackolik.ui.livescore.LiveScoreFragment
import com.zeynelerdi.mackolik.ui.news.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseFragmentActivity(), MainNavigator {

    private lateinit var mainActivityDataBinding: ActivityMainBinding
    private val newsFragment = NewsFragment()
    private val liveScoreFragment: LiveScoreFragment = LiveScoreFragment()

    @Inject
    lateinit var mainViewModel: MainViewModel
    override var frameLayoutId: Int
        get() = R.id.fragmentContainer
        set(value) {}

    override fun onCreateActivity(savedInstanceState: Bundle?): ViewGroup {
        mainActivityDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        return rootMainActivity as ViewGroup
    }

    override fun bindView() {
        setSpinnerListener()
        initView(NewsFragment())
    }

    private fun setSpinnerListener() {
        mackolikSpinner.onSpinnerItemSelectedListener = spinnerItemSelectedListener
    }

    private fun clearSpinnerListener(){
        mackolikSpinner.onSpinnerItemSelectedListener = null
    }

    private val spinnerItemSelectedListener: OnSpinnerItemSelectedListener =
        object : OnSpinnerItemSelectedListener {
            override fun onItemSelected(
                    parent: MackolikSpinner?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                when (position) {
                    0 -> {
                        changeFragment(newsFragment)
                    }
                    1-> {
                        changeFragment(liveScoreFragment)
                    }

                }
            }

        }
}
