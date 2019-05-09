package com.example.myapplication.responses

import com.google.gson.annotations.SerializedName

class WeatherInfo {

/*
    @SerializedName("EffectiveEpochTime")
    var epochTime:Int=0
    @SerializedName("Text")
    lateinit var text:String
*/
    @SerializedName("DailyForecasts")
    lateinit var dailyForecast:List<DailyInfo>

public fun getDay(dayNum:Int):DailyInfo{
    return dailyForecast.get(dayNum);
}


}