package com.codechallenge.nearshoretest.ui.chardetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.codechallenge.nearshoretest.MainActivity
import com.codechallenge.nearshoretest.R
import com.codechallenge.nearshoretest.collect
import com.codechallenge.nearshoretest.collectLast
import com.codechallenge.nearshoretest.common.FooterAdapter
import com.codechallenge.nearshoretest.databinding.FragmentChardetailBinding
import com.codechallenge.nearshoretest.executeWithAction
import com.codechallenge.nearshoretest.model.models.characters.MarvelChar
import com.codechallenge.nearshoretest.model.models.comics.Comic
import com.codechallenge.nearshoretest.model.models.eventseriesstories.EventSeriesStories
import com.codechallenge.nearshoretest.ui.chardetail.adapter.ComicsAdapter
import com.codechallenge.nearshoretest.ui.chardetail.adapter.EventSeriesStoriesAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map

class CharDetailFragment: Fragment() {
    private var _binding: FragmentChardetailBinding? = null
    private val charDetailViewModel : CharDetailViewModel by viewModels()

    private val binding get() = _binding!!

    private lateinit var charData : MarvelChar

    private lateinit var comicsAdapter : ComicsAdapter
    private lateinit var eventAdapter: EventSeriesStoriesAdapter
    private lateinit var storiesAdapter : EventSeriesStoriesAdapter
    private lateinit var seriesAdapter: EventSeriesStoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        postponeEnterTransition()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as MainActivity).changeBottomNavigationVisibility(false)
        _binding = FragmentChardetailBinding.inflate(inflater, container, false)

        charData = arguments?.getParcelable("charData", MarvelChar::class.java)!!
        setAdapters()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        searchComics()
        searchEvents()
        searchSeries()
        searchStories()
    }

    private fun initViews() {
        binding.charpic.apply {
            val imageUri = "${charData.thumbnail.path}.${charData.thumbnail.extension}"
            transitionName = charData.thumbnail.path
            Picasso.get().load(imageUri).placeholder(R.drawable.logo_marvel).fit().centerCrop().into(this, object: Callback{
                override fun onSuccess() {
                    startPostponedEnterTransition()
                }

                override fun onError(e: Exception?) {
                    startPostponedEnterTransition()
                }

            })
        }
        binding.close.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.charname.text = charData.name
        binding.description.text = charData.description.ifEmpty { requireContext().resources.getString(R.string.na) }
    }
    private fun setAdapters() {
        comicsAdapter = ComicsAdapter()
        eventAdapter = EventSeriesStoriesAdapter()
        seriesAdapter = EventSeriesStoriesAdapter()
        storiesAdapter = EventSeriesStoriesAdapter()

        collect(flow = comicsAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setComicUiState
        )
        binding.comicsRecList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.comicsRecList.adapter = comicsAdapter.withLoadStateFooter(FooterAdapter(comicsAdapter::retry))
        binding.comicsbtnRetry.setOnClickListener { comicsAdapter.retry() }

        collect(flow = eventAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setEventUiState
        )
        binding.eventsRecList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.eventsRecList.adapter = eventAdapter.withLoadStateFooter(FooterAdapter(eventAdapter::retry))
        binding.eventsbtnRetry.setOnClickListener { eventAdapter.retry() }

        collect(flow = seriesAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setSeriesUiState
        )
        binding.seriesRecList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.seriesRecList.adapter = seriesAdapter.withLoadStateFooter(FooterAdapter(seriesAdapter::retry))
        binding.seriesbtnRetry.setOnClickListener { seriesAdapter.retry() }

        collect(flow = storiesAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setStoriesUiState
        )
        binding.storiesRecList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.storiesRecList.adapter = storiesAdapter.withLoadStateFooter(FooterAdapter(storiesAdapter::retry))
        binding.storiesbtnRetry.setOnClickListener { storiesAdapter.retry() }
    }

    private fun setComicUiState(loadState: LoadState) {
        binding.executeWithAction {
            binding.comicsUiState = CharDetailUiState(loadState)
        }
    }

    private fun setEventUiState(loadState: LoadState) {
        binding.executeWithAction {
            binding.eventsUiState = CharDetailUiState(loadState)
        }
    }

    private fun setSeriesUiState(loadState: LoadState) {
        binding.executeWithAction {
            binding.seriesUiState = CharDetailUiState(loadState)
        }
    }

    private fun setStoriesUiState(loadState: LoadState) {
        binding.executeWithAction {
            binding.storiesUiState = CharDetailUiState(loadState)
        }
    }

    private fun searchComics() {
        collectLast(charDetailViewModel.getComics(charData.id), ::setComics)
    }

    private fun searchEvents() {
        collectLast(charDetailViewModel.getEvents(charData.id), ::setEvents)
    }

    private fun searchStories() {
        collectLast(charDetailViewModel.getStories(charData.id), ::setStories)
    }

    private fun searchSeries() {
        collectLast(charDetailViewModel.getSeries(charData.id), ::setSeries)
    }

    private suspend fun setComics(pagingData: PagingData<Comic>) {
        comicsAdapter.submitData(pagingData)
    }

    private suspend fun setEvents(pagingData: PagingData<EventSeriesStories>) {
        eventAdapter.submitData(pagingData)
    }

    private suspend fun setSeries(pagingData: PagingData<EventSeriesStories>) {
        seriesAdapter.submitData(pagingData)
    }

    private suspend fun setStories(pagingData: PagingData<EventSeriesStories>) {
        storiesAdapter.submitData(pagingData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}