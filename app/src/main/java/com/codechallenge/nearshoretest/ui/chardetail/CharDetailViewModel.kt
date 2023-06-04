package com.codechallenge.nearshoretest.ui.chardetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.codechallenge.nearshoretest.model.Repository
import com.codechallenge.nearshoretest.model.models.comics.Comic
import com.codechallenge.nearshoretest.model.models.eventseriesstories.EventSeriesStories
import kotlinx.coroutines.flow.Flow

class CharDetailViewModel : ViewModel() {
    private val repository: Repository = Repository.getInstance()

    fun getComics(id: Int) : Flow<PagingData<Comic>> {
        return repository.getComics(id).cachedIn(viewModelScope)
    }

    fun getEvents(id: Int) : Flow<PagingData<EventSeriesStories>> {
        return repository.getEvents(id).cachedIn(viewModelScope)
    }

    fun getSeries(id: Int) : Flow<PagingData<EventSeriesStories>> {
        return repository.getSeries(id).cachedIn(viewModelScope)
    }

    fun getStories(id: Int) : Flow<PagingData<EventSeriesStories>> {
        return repository.getStories(id).cachedIn(viewModelScope)
    }
}