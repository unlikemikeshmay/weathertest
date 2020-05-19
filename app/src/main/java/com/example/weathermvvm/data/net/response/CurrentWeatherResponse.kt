package com.example.weathermvvm.data.net.response

import com.example.weathermvvm.data.net.Request
import com.example.weathermvvm.data.db.entity.CurrentWeatherEntry
import com.example.weathermvvm.data.db.entity.Location
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location,
    val request: Request
)