package com.maverick.simplegraphqldemo.data.client

import com.apollographql.apollo3.ApolloClient
import com.maverick.graphqldemo.CountryListQuery
import com.maverick.graphqldemo.CountryQuery
import com.maverick.simplegraphqldemo.data.mapper.toDetailedCountry
import com.maverick.simplegraphqldemo.data.mapper.toSimpleCountry
import com.maverick.simplegraphqldemo.data.api.CountryClient
import com.maverick.simplegraphqldemo.domain.DetailedCountry
import com.maverick.simplegraphqldemo.domain.SimpleCountry

class ApolloCountryClient : CountryClient {

    private val apolloClient = ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/graphql")
        .build()

    override suspend fun getCountryList(): List<SimpleCountry> {
        return apolloClient.query(CountryListQuery())
            .execute()
            .data
            ?.countries
            ?.map {
                it.toSimpleCountry()
            } ?: emptyList()
    }

    override suspend fun getCountryDetails(code: String): DetailedCountry? {
        return apolloClient.query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }

}