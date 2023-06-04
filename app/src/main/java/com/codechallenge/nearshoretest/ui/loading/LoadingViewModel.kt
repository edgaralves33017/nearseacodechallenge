package com.codechallenge.nearshoretest.ui.loading

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codechallenge.nearshoretest.model.Repository
import com.codechallenge.nearshoretest.model.models.characters.MarvelChar
import com.codechallenge.nearshoretest.model.network.api.ApiResult
import kotlinx.coroutines.launch

class LoadingViewModel : ViewModel() {
    private val repository: Repository = Repository.getInstance()
}