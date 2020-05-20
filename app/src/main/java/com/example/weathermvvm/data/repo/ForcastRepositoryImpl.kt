package com.example.weathermvvm.data.repo

import androidx.lifecycle.LiveData
import androidx.room.PrimaryKey
import com.example.weathermvvm.data.CurrentWeatherDao
import com.example.weathermvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntity
import com.example.weathermvvm.data.net.WeatherNetworkDataSource
import com.example.weathermvvm.data.net.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ForcastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForcastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever{
            newCurrentWeather ->
            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(unit: Boolean): LiveData<UnitSpecificCurrentWeatherEntity> {
       return withContext(Dispatchers.IO){
           return@withContext if ()
       }
    }
    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse){
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)

        }
    }
}