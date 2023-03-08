package com.maverick.simplegraphqldemo.data.usecase

import com.maverick.simplegraphqldemo.data.client.ApolloCountryClient
import com.maverick.simplegraphqldemo.domain.SimpleCountry

class GetCountryListUseCase {

    private val countryClient = ApolloCountryClient()

    suspend fun getCountryList(): List<SimpleCountry> {
        return countryClient
            .getCountryList()
            .sortedBy { it.name }
    }

}