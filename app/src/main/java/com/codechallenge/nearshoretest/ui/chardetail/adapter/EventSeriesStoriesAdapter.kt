package com.codechallenge.nearshoretest.ui.chardetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.codechallenge.nearshoretest.R
import com.codechallenge.nearshoretest.model.models.eventseriesstories.EventSeriesStories
import com.squareup.picasso.Picasso


/**
 * Class adapter for the recycler view
 */
class EventSeriesStoriesAdapter :
    PagingDataAdapter<EventSeriesStories, EventSeriesStoriesAdapter.ViewHolder>(Comparator) {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_smaller_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        getItem(position)?.let {
            val url = "${it.thumbnail?.path}.${it.thumbnail?.extension}"
            holder.pic.apply {
                Picasso.get().load(url).placeholder(R.drawable.ic_missing_comic).fit().centerCrop().into(this)
            }


            holder.title.text = it.title
            holder.desc.text = it.description
        }
    }

    object Comparator : DiffUtil.ItemCallback<EventSeriesStories>() {
        override fun areItemsTheSame(oldItem: EventSeriesStories, newItem: EventSeriesStories): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: EventSeriesStories,
            newItem: EventSeriesStories
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var pic: ImageView
        var title: TextView
        var desc: TextView

        init {
            pic = itemView.findViewById(R.id.pic)
            title = itemView.findViewById(R.id.title)
            desc = itemView.findViewById(R.id.description)
        }
    }
}