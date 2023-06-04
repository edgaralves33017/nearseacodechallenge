package com.codechallenge.nearshoretest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.codechallenge.nearshoretest.MainActivity
import com.codechallenge.nearshoretest.R
import com.codechallenge.nearshoretest.collect
import com.codechallenge.nearshoretest.collectLast
import com.codechallenge.nearshoretest.common.FooterAdapter
import com.codechallenge.nearshoretest.databinding.FragmentHomeBinding
import com.codechallenge.nearshoretest.executeWithAction
import com.codechallenge.nearshoretest.model.models.characters.MarvelChar
import com.codechallenge.nearshoretest.ui.home.adapter.HomeFragmentAdapter
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

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

        (activity as MainActivity).changeBottomNavigationVisibility(true)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setAdapter()
        setListener()
        setOnQuerySearchListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val parsedData = homeViewModel.getParsedData()
        if (parsedData != null) {
            lifecycleScope.launch {
                setCharacters(parsedData)
            }
        }
        else {
            searchCharacters(null)
        }
    }

    private fun setOnQuerySearchListener() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                //Null check because Retrofit ignores query parameters that are passed as null
                //this is to avoid "&name="
                val parsedQuery = if (query.isNullOrBlank()) null else query
                searchCharacters(parsedQuery)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun searchCharacters(name: String?) {
        homeAdapter.submitData(lifecycle, PagingData.empty())

        collectLast(homeViewModel.getCharacters(name), ::setCharacters)
    }

    private fun setListener() {
        binding.btnRetry.setOnClickListener { homeAdapter.retry() }
    }

    private fun setAdapter() {
        homeAdapter = HomeFragmentAdapter(::cardClickListener)
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
        homeViewModel.setParsedData(charactersItemsPagingData)
        homeAdapter.submitData(charactersItemsPagingData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun cardClickListener(imgView: ImageView, charData: MarvelChar) {
        val extras = FragmentNavigatorExtras(
            imgView to charData.thumbnail.path
        )
        val bundle = bundleOf(
            "charData" to charData
        )
        findNavController().navigate(R.id.action_Home_to_CharDetail, bundle, null, extras)
    }
}