package com.codechallenge.nearshoretest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.codechallenge.nearshoretest.model.Repository
import com.codechallenge.nearshoretest.model.models.characters.MarvelChar
import com.codechallenge.nearshoretest.model.network.api.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository: Repository = Repository.getInstance()

    /**
     * [MutableLiveData] that will contain the states emitted by flow.
     */
    private var _loadState: MutableLiveData<ApiResult<List<MarvelChar>>> =
        MutableLiveData<ApiResult<List<MarvelChar>>>()

    /**
     * [LiveData] that will be used by the fragment to discern the flow states.
     */
    val loadState: LiveData<ApiResult<List<MarvelChar>>> = _loadState

    /**
     * Starts download of the CSV file.
     */
    fun getCharacters(name: String?) : Flow<PagingData<MarvelChar>> {
        return repository.getCharacters(name).cachedIn(viewModelScope)


        /*viewModelScope.launch {
            repository.getCharacters(1, "").collect {
                _loadState.postValue(it)
            }
        }*/
    }
}