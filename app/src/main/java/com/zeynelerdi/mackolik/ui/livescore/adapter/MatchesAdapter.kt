package com.zeynelerdi.mackolik.ui.livescore.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zeynelerdi.mackolik.R
import com.zeynelerdi.mackolik.ui.livescore.model.data.LiveScoreData
import com.zeynelerdi.mackolik.util.extension.*
import kotlinx.android.synthetic.main.list_item_matches.view.*
import kotlinx.android.synthetic.main.list_item_matches_title.view.*

class MatchesAdapter(
    private var list: ArrayList<LiveScoreData>,
    private val clickListener: ((LiveScoreData) -> Unit)?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0){
            return MatchesTitleViewHolder(parent)
        }
        return MatchesViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MatchesViewHolder) {
            holder.bind(list[position], position)
            holder.itemView.setOnClickListener {
                clickListener?.let { it1 -> it1(list[position]) }
            }
        }else if (holder is MatchesTitleViewHolder){
            holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return if (list[position].title.isNotEmpty()){
            0
        }else{
            1
        }
    }

    fun setMatchList(list: ArrayList<LiveScoreData>){
        if (this.list.isEmpty()){
            this.list = list
            notifyDataSetChanged()
        }else{
            val diffResult = DiffUtil.calculateDiff(MatchesDiffUtil(this.list, list))
            this.list.clear()
            this.list.addAll(list)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    class MatchesViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        constructor(parent: ViewGroup) :
                this(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_matches, parent, false)
                )

        @SuppressLint("SetTextI18n")
        fun bind(item: LiveScoreData, position: Int) {
            itemView.let {
                item.let {it1 ->
                    it.teamATV.text = it1.matches?.team_A?.display_name
                    it.scoreTV.text = it1.matches?.fts_A.toString()+"-"+it1.matches?.fts_B.toString()
                    it.teamBTV.text = it1.matches?.team_B?.display_name
                }
                if (position%2==0){
                    it.rootView.setBackgroundColor(ContextCompat.getColor(it.context, R.color.gray_dc))
                }else{
                    it.rootView.setBackgroundColor(ContextCompat.getColor(it.context, R.color.white))
                }
            }
        }
    }

    class MatchesTitleViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        constructor(parent: ViewGroup) :
                this(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_matches_title, parent, false)
                )

        @SuppressLint("SetTextI18n")
        fun bind(item: LiveScoreData) {
            itemView.dateTV.text = item.title
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter(
            value = ["bind:setMatches", "bind:setMatchesClickListener"],
            requireAll = false
        )
        fun setMatches(
            view: RecyclerView?,
            list: List<LiveScoreData>?,
            clickListener: (LiveScoreData) -> Unit
        ) {
            view?.let { v ->
                v.adapter.notNull {
                    v.adapter?.let {it ->
                        list?.let { it1->
                            (it as MatchesAdapter).setMatchList(it1 as ArrayList<LiveScoreData>)
                        }
                    }
                }
                v.adapter.isNull {
                    v.layoutManager = LinearLayoutManager(v.context, RecyclerView.VERTICAL, false)
                    list?.let { it1->
                        v.adapter = MatchesAdapter(list as ArrayList<LiveScoreData>, clickListener)
                    }
                }
            }
        }
    }
}