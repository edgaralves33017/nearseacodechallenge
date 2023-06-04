package com.codechallenge.nearshoretest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.codechallenge.nearshoretest.model.Repository
import com.codechallenge.nearshoretest.model.models.characters.MarvelChar
import kotlinx.coroutines.flow.Flow

class HomeViewModel : ViewModel() {
    private val repository: Repository = Repository.getInstance()

    /**
     * Starts download of the CSV file.
     */
    fun getCharacters(name: String?) : Flow<PagingData<MarvelChar>> {
        return repository.getCharacters(name).cachedIn(viewModelScope)
    }
}