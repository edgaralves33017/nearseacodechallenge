package com.codechallenge.nearshoretest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.codechallenge.nearshoretest.databinding.FragmentHomeBinding
import com.codechallenge.nearshoretest.model.models.characters.MarvelChar
import com.codechallenge.nearshoretest.collect
import com.codechallenge.nearshoretest.collectLast
import com.codechallenge.nearshoretest.common.FooterAdapter
import com.codechallenge.nearshoretest.executeWithAction
import com.codechallenge.nearshoretest.ui.home.adapter.HomeFragmentAdapter
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel : HomeViewModel by viewModels()

    private val binding get() = _binding!!

    private lateinit var homeAdapter : HomeFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        setAdapter()
        setListener()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectLast(homeViewModel.getCharacters(null), ::setCharacters)
    }

    private fun setListener() {
        binding.btnRetry.setOnClickListener { homeAdapter.retry() }
    }

    private fun setAdapter() {
        homeAdapter = HomeFragmentAdapter()
        collect(flow = homeAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setHomeUiState
        )
        binding.charRecView.layoutManager = LinearLayoutManager(context);
        binding.charRecView.adapter = homeAdapter.withLoadStateFooter(FooterAdapter(homeAdapter::retry))
    }

    private fun setHomeUiState(loadState: LoadState) {
        binding.executeWithAction {
            binding.homeUiState = HomeFragmentUiState(loadState)
        }
    }

    private suspend fun setCharacters(charactersItemsPagingData: PagingData<MarvelChar>) {
        homeAdapter.submitData(charactersItemsPagingData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}