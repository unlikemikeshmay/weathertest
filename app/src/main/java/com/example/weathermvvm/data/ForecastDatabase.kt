package com.example.weathermvvm.data

import android.content.Context
import androidx.room.*
import com.example.weathermvvm.data.db.entity.Converter
import com.example.weathermvvm.data.db.entity.CurrentWeatherEntry


@Database(
    entities = [CurrentWeatherEntry::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class ForecastDatabase : RoomDatabase(){
    abstract fun currentWeatherDao(): CurrentWeatherDao

    companion object{
        @Volatile private var instance: ForecastDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance  ?: buildDatabase(context).also { instance = it }
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            ForecastDatabase::class.java,
            "forcast.db")
                .build()
    }
}