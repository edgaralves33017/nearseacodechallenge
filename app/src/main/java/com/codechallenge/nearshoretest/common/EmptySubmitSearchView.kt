package com.codechallenge.nearshoretest.common

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.widget.TextView
import androidx.appcompat.R
import androidx.appcompat.widget.SearchView

/**
 * Extends SearchView
 *
 * This is done so SearchView can accept empty queries to show all results
 */
class EmptySubmitSearchView : SearchView {
    var mSearchSrcTextView: SearchAutoComplete? = null
    var listener: OnQueryTextListener? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun setOnQueryTextListener(listener: OnQueryTextListener) {
        super.setOnQueryTextListener(listener)
        this.listener = listener
        mSearchSrcTextView = findViewById(R.id.search_src_text)
        mSearchSrcTextView!!.setOnEditorActionListener { _: TextView?, _: Int, _: KeyEvent? ->
            listener.onQueryTextSubmit(query.toString())
            true
        }
    }
}