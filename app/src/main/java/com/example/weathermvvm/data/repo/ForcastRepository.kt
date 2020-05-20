package com.example.weathermvvm.data.repo

import androidx.lifecycle.LiveData
import com.example.weathermvvm.data.db.entity.CurrentWeatherEntry
import com.example.weathermvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntity

interface ForcastRepository {
    suspend fun getCurrentWeather(unit:Boolean): LiveData<UnitSpecificCurrentWeatherEntity>
}