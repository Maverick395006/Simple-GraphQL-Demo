package com.maverick.simplegraphqldemo.data.mapper

import com.maverick.graphqldemo.CountryListQuery
import com.maverick.graphqldemo.CountryQuery
import com.maverick.simplegraphqldemo.domain.DetailedCountry
import com.maverick.simplegraphqldemo.domain.SimpleCountry

fun CountryListQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No Capital"
    )
}

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No Capital",
        currency = currency ?: "No Currency",
        languages = languages.mapNotNull { it.name },
        continent = continent.name
    )
}