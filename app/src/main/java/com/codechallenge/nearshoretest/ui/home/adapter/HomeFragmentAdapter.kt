package com.codechallenge.nearshoretest.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.codechallenge.nearshoretest.R
import com.codechallenge.nearshoretest.model.models.characters.MarvelChar

/**
 * Class adapter for the recycler view
 */
class HomeFragmentAdapter :
    PagingDataAdapter<MarvelChar, HomeFragmentAdapter.ViewHolder>(Comparator) {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_home_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        getItem(position)?.let {
            val url = "${it.thumbnail.path}.${it.thumbnail.extension}"
            Glide
                .with(context)
                .load(url)
                .placeholder(R.drawable.logo_marvel)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.charpic)

            holder.charname.text = it.name
        }
    }

    object Comparator : DiffUtil.ItemCallback<MarvelChar>() {
        override fun areItemsTheSame(oldItem: MarvelChar, newItem: MarvelChar): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MarvelChar,
            newItem: MarvelChar
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var charpic: ImageView
        var charname: TextView

        init {
            charpic = itemView.findViewById(R.id.charpic)
            charname = itemView.findViewById(R.id.charname)
        }
    }
}