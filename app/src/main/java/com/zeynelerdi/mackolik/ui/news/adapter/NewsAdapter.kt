package com.zeynelerdi.mackolik.ui.news.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zeynelerdi.mackolik.R
import com.zeynelerdi.mackolik.ui.news.model.response.News
import com.zeynelerdi.mackolik.util.decoration.RecyclerViewMarginDecoration
import com.zeynelerdi.mackolik.util.extension.*
import kotlinx.android.synthetic.main.list_item_news.view.*

class NewsAdapter(
    private val list: ArrayList<News>,
    private val clickListener: ((News) -> Unit)?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsViewHolder).bind(
            list[position]
        )
        holder.itemView.setOnClickListener {
            clickListener?.let { it1 -> it1(list[position]) }
        }
    }

    override fun getItemCount(): Int = list.size

    class NewsViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        constructor(parent: ViewGroup) :
                this(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_news, parent, false)
                )

        @SuppressLint("SetTextI18n")
        fun bind(item: News) {
            itemView.let { it ->
                item.let {it1 ->
                    it1.picUrl?.isNotEmpty {it2 ->
                        it.newsIV.loadImage(itemView.context,it2,null)
                    }
                    it.descTV.text = it1.title
                    it.dateTV.text = it1.date?.toDateForNews
                }
            }
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter(
            value = ["bind:setNews", "bind:setNewsClickListener"],
            requireAll = false
        )
        fun setNews(
            view: RecyclerView?,
            list: List<News>?,
            clickListener: (News) -> Unit
        ) {
            view?.let { v ->
                v.adapter.isNull {
                    v.addItemDecoration(RecyclerViewMarginDecoration(3,0, 0, 0))
                    v.layoutManager = LinearLayoutManager(v.context, RecyclerView.VERTICAL, false)
                }
                v.let {
                    list?.let {it1->
                        it.adapter = NewsAdapter(it1 as ArrayList<News>, clickListener)
                        it.adapter?.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}