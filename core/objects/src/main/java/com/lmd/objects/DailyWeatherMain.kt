package com.lmd.objects

data class DailyWeatherMain(
    val lat: Double,
    val lon: Double,
    val timezone: String,
//    val current: CurrentWeather,
//    val daily: List<DailyDayWeather>,
//    val hourly: List<HourlyWeather>,

    val timezoneOffset: Int
)
