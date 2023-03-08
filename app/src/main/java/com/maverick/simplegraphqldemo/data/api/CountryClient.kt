package com.maverick.simplegraphqldemo.data.api

import com.maverick.simplegraphqldemo.domain.DetailedCountry
import com.maverick.simplegraphqldemo.domain.SimpleCountry

interface CountryClient {

    suspend fun getCountryList(): List<SimpleCountry>

    suspend fun getCountryDetails(code: String): DetailedCountry?

}