package com.maverick.simplegraphqldemo.repository

import android.util.Log
import com.google.gson.Gson
import com.maverick.simplegraphqldemo.data.usecase.GetCountryListUseCase
import com.maverick.simplegraphqldemo.data.usecase.GetCountryUseCase
import com.maverick.simplegraphqldemo.domain.DetailedCountry
import com.maverick.simplegraphqldemo.domain.SimpleCountry
import com.maverick.simplegraphqldemo.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CountryRepository {

    private val getCountriesUseCase = GetCountryListUseCase()
    private val getCountryUseCase = GetCountryUseCase()

    suspend fun getCountryList(): Flow<DataState<List<SimpleCountry>>> = flow {

        // Loading State
        emit(DataState.Loading)
        try {
            // get data by Api Call
            val networkCountryList = getCountriesUseCase.getCountryList()
            Log.d("API Response", Gson().toJson(networkCountryList))

            // Success State: Finally emit data  as Single Source of truth
            emit(DataState.Success(networkCountryList))
        } catch (e: Exception) {
            // Error State
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getCountry(code: String): Flow<DataState<DetailedCountry>> = flow {

        // Loading State
        emit(DataState.Loading)
        try {
            // get data by Api Call
            val networkCountry = getCountryUseCase.getCountryDetails(code)
            Log.d("API Response", Gson().toJson(networkCountry))

            // Success State: Finally emit data  as Single Source of truth
            emit(DataState.Success(networkCountry!!))
        } catch (e: Exception) {
            // Error State
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

}