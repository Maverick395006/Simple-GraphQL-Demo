package com.maverick.simplegraphqldemo.domain

data class DetailedCountry(
    val code:String,
    val name:String,
    val emoji:String,
    val capital:String,
    val currency:String,
    val languages:List<String>,
    val continent:String,
)
