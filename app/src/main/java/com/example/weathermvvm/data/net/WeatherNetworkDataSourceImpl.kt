package com.example.weathermvvm.data.net
import android.util.Log
import com.example.weathermvvm.data.net.WeatherApiService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.data.internal.NoConnectivityException
import com.example.weathermvvm.data.net.response.CurrentWeatherResponse

class WeatherNetworkDataSourceImpl(
    private val weatherApiService: WeatherApiService
) : WeatherNetworkDataSource {

    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override suspend fun fetchCurrentWeather(location: String) {
        try {
            val fetchedCurrentWeather = weatherApiService.getCurrentWeather(location )
                .await()
            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        }
        catch (e:NoConnectivityException){
            Log.e("Connectivity","No internet connection.",e)
        }
    }
}