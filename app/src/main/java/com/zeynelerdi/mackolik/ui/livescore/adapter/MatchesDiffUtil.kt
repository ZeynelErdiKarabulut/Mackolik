package com.zeynelerdi.mackolik.ui.livescore.adapter

import androidx.recyclerview.widget.DiffUtil
import com.zeynelerdi.mackolik.ui.livescore.model.data.LiveScoreData

class MatchesDiffUtil(
    private val oldList: ArrayList<LiveScoreData>,
    private val newList: ArrayList<LiveScoreData>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMatch = oldList[oldItemPosition]
        val newMatch = newList[newItemPosition]
        return oldMatch.id == newMatch.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMatch = oldList[oldItemPosition]
        val newMatch = newList[newItemPosition]
        return if (oldMatch.matches != null && newMatch.matches != null) {
            oldMatch.matches?.fts_A == newMatch.matches?.fts_A && oldMatch.matches?.fts_B == newMatch.matches?.fts_B
        }else false
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}