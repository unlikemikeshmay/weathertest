package com.example.weathermvvm.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.util.function.DoubleBinaryOperator

const val CURRENT_WEATHER_ID = 0
@Entity(tableName="current_weather")
data class CurrentWeatherEntry(

    val feelslike: Double,

    @SerializedName("is_day")
    val isDay: String,
    @SerializedName("observation_time")
    val observationTime: String,
    val precip: Double,

    val temperature: Double,
    @SerializedName("uv_index")
    val uvIndex: Int,
    val visibility: Int,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("weather_descriptions")
    var weatherDescriptions: MutableList<String>,
    @SerializedName("weather_icons")
    var weatherIcons: MutableList<String>,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_speed")
    val windSpeed: Double
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID
}
class Converter{
    companion object{
        @TypeConverter
        @JvmStatic
        fun listToString(weatherDescriptions: MutableList<String>):String{
            return weatherDescriptions.joinToString(",")
        }
        @TypeConverter
        @JvmStatic
        fun stringToList(str:String):MutableList<String>{
            return str.split(",").toMutableList()
        }
    }
}