package com.codechallenge.nearshoretest.ui.chardetail.adapter

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
import com.codechallenge.nearshoretest.model.models.comics.Comic
import com.squareup.picasso.Picasso

/**
 * Class adapter for the recycler view
 */
class ComicsAdapter :
    PagingDataAdapter<Comic, ComicsAdapter.ViewHolder>(Comparator) {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_comic, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        getItem(position)?.let {
            val url = "${it.thumbnail.path}.${it.thumbnail.extension}"
            holder.comicPic.apply {
                Picasso.get().load(url).placeholder(R.drawable.ic_missing_comic).fit().centerCrop().into(this)
            }


            holder.comicName.text = it.title
        }
    }

    object Comparator : DiffUtil.ItemCallback<Comic>() {
        override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Comic,
            newItem: Comic
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var comicPic: ImageView
        var comicName: TextView

        init {
            comicPic = itemView.findViewById(R.id.comicPic)
            comicName = itemView.findViewById(R.id.comicName)
        }
    }
}