package com.zeynelerdi.mackolik.ui.livescore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.zeynelerdi.mackolik.R
import com.zeynelerdi.mackolik.databinding.FragmentLiveScoreBinding
import com.zeynelerdi.mackolik.ui.common.base.view.BaseFragment
import com.zeynelerdi.mackolik.ui.livescore.model.response.Matches
import javax.inject.Inject

class LiveScoreFragment : BaseFragment(), LiveScoreNavigator {

    private lateinit var mLiveScoreFragmentBinding: FragmentLiveScoreBinding

    @Inject
    lateinit var mLiveScoreViewModel: LiveScoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mLiveScoreFragmentBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_live_score,
                container,
                false
            )
        mLiveScoreViewModel.navigator = this
        mLiveScoreFragmentBinding.lifecycleOwner = this
        mLiveScoreFragmentBinding.viewModel = mLiveScoreViewModel
        return mLiveScoreFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onMatchClick(match: Matches) {
        //go match detail
    }

    override fun onResume() {
        super.onResume()
        mLiveScoreViewModel.getScores()
    }

    override fun onPause() {
        mLiveScoreViewModel.stopRepeating()
        super.onPause()
    }
}