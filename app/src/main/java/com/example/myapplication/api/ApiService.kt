package com.example.myapplication.api

import com.example.myapplication.responses.WeatherInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

import retrofit2.http.Query

interface ApiService {

@GET("/forecasts/v1/daily/5day/{locationID}")
fun get5DayWeather(
    @Path("locationID")locationID:Int,
    @Query("apikey") apikey:String,
    @Query("metric") metric:String): Call<WeatherInfo>



}

//forecasts/v1/daily/5day/298740?apikey=TpXJFQ2vKSD4YhG3cL19fsGgpgcQZzMR&metric=true



//349727 new york
//328328 london
//178087 berlin
//623 paris
//308526 madrid\
//298740 prishtina

