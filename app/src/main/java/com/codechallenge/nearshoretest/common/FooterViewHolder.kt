package com.codechallenge.nearshoretest.common

import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.codechallenge.nearshoretest.databinding.ItemPagingFooterBinding
import com.codechallenge.nearshoretest.executeWithAction

class FooterViewHolder(
    private val binding: ItemPagingFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        binding.executeWithAction {
            binding.footerUiState = FooterUiState(loadState)
        }
    }
}