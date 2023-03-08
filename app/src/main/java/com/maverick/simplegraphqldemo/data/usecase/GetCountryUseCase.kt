package com.maverick.simplegraphqldemo.data.usecase

import com.maverick.simplegraphqldemo.data.client.ApolloCountryClient
import com.maverick.simplegraphqldemo.domain.DetailedCountry

class GetCountryUseCase {

    private val countryClient = ApolloCountryClient()

    suspend fun getCountryDetails(code: String): DetailedCountry? {
        return countryClient.getCountryDetails(code)
    }

}