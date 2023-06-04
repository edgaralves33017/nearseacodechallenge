package com.codechallenge.nearshoretest.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.codechallenge.nearshoretest.R
import com.codechallenge.nearshoretest.model.models.characters.MarvelChar
import com.squareup.picasso.Picasso

/**
 * Class adapter for the recycler view
 */
class HomeFragmentAdapter(val clickListener: (imgView: ImageView, charData: MarvelChar)-> Unit) :
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
            holder.charpic.apply {
                transitionName = it.thumbnail.path
                Picasso.get().load(url).placeholder(R.drawable.logo_marvel).fit().centerCrop().into(this)
            }


            holder.charname.text = it.name

            holder.cardContainer.setOnClickListener { _->
                clickListener.invoke(holder.charpic, it)
            }
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
        var cardContainer : CardView

        init {
            charpic = itemView.findViewById(R.id.charpic)
            charname = itemView.findViewById(R.id.charname)
            cardContainer = itemView.findViewById(R.id.cardContainer)
        }
    }
}