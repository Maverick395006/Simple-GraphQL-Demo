package com.maverick.simplegraphqldemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverick.simplegraphqldemo.domain.SimpleCountry
import com.maverick.simplegraphqldemo.repository.CountryRepository
import com.maverick.simplegraphqldemo.utils.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var mainRepository: CountryRepository = CountryRepository()

    private val _dataState: MutableLiveData<DataState<List<SimpleCountry>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<SimpleCountry>>> = _dataState


    fun getCountryList() {
        viewModelScope.launch {
            mainRepository.getCountryList()
                .onEach { dataState ->
                    _dataState.value = dataState
                }
                .launchIn(viewModelScope)
        }
    }

}