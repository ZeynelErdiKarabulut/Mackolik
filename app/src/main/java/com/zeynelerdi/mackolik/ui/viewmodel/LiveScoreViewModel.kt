package com.zeynelerdi.mackolik.ui.livescore

import androidx.databinding.ObservableField
import com.zeynelerdi.mackolik.data.manager.DataManagerImp
import com.zeynelerdi.mackolik.data.remote.service.plusAssign
import com.zeynelerdi.mackolik.ui.common.base.BaseViewModel
import com.zeynelerdi.mackolik.ui.livescore.model.data.LiveScoreData
import com.zeynelerdi.mackolik.ui.livescore.model.response.Matches
import com.zeynelerdi.mackolik.util.extension.toDateForLiveScore
import com.zeynelerdi.mackolik.util.extension.toDateTimeForLiveScore
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LiveScoreViewModel @Inject constructor(private var dataManagerImp: DataManagerImp) :
    BaseViewModel<LiveScoreNavigator>() {

    var liveScores = ObservableField<List<LiveScoreData?>?>()
    private var shouldStopRepeating = false

    fun getScores() {
        shouldStopRepeating = false
        liveScores.set(null)
        navigator?.showLoading()

        dataManagerImp.apiHelperImp.getLiveScores().repeatWhen{
                observable -> observable.delay(30, TimeUnit.SECONDS)
        }.takeUntil { shouldStopRepeating }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                liveScores.set(getAdapterModel(it?.data?.matches))
                navigator?.hideLoading()
            }.also {
                disposable += it
            }
    }

    var setMatchClickListener: (matches: LiveScoreData) -> Unit = { i: LiveScoreData ->
        navigator?.onMatchClick(i.matches!!)
    }

    fun stopRepeating(){
        shouldStopRepeating = true
    }

    private fun getAdapterModel(matches: List<Matches?>?): List<LiveScoreData?>{
        val tempList: ArrayList<LiveScoreData?> = arrayListOf()
        matches?.let { list ->
            list.sortedBy { it?.date_time_utc?.toDateTimeForLiveScore }
            tempList.add(LiveScoreData(list[0]?.id!!, null, list[0]?.date_time_utc?.toDateForLiveScore!!))
            for (i in 0 until list.size-1){
                if (!list[i]?.date_time_utc?.toDateForLiveScore?.equals(list[i+1]?.date_time_utc?.toDateForLiveScore)!!){
                    tempList.add(LiveScoreData(list[i]?.id!!, null, list[i]?.date_time_utc?.toDateForLiveScore!!))
                }
                tempList.add(LiveScoreData(list[i]?.id!!, list[i], ""))
            }
        }
        return tempList
    }
}
