package com.example.weathermvvm.data

import com.example.weathermvvm.data.net.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "cf30ce9dd3bd11d4901bbf21e7c0dd39"

//http://api.weatherstack.com/current?access_key=cf30ce9dd3bd11d4901bbf21e7c0dd39&query=Toronto
interface WeatherApiService {

    @GET("current")
    fun getCurrentWeather(

        @Query("query") location: String
        //@Query("language")language:String = "en"

    ): Deferred<CurrentWeatherResponse>
    companion object {
        operator fun invoke():WeatherApiService{
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .addQueryParameter("units","m")
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiService::class.java)

        }
    }
}