package com.example.weathermvvm.data.net

import androidx.lifecycle.LiveData
import com.example.weathermvvm.data.net.response.CurrentWeatherResponse

interface WeatherNetworkDataSource{
val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location:String
    )
}