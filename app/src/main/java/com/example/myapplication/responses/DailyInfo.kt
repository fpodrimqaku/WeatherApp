package com.example.myapplication.responses

import com.google.gson.annotations.SerializedName
import kotlin.math.roundToInt

class DailyInfo {


@SerializedName("Temperature")
lateinit var temperatureValues: TemperatureValues

    @SerializedName("Day")
    lateinit var day:Period

    @SerializedName("Night")
    lateinit var night:Period



    class Temperature {

        @SerializedName("Value")
        var value:Float=0f
        @SerializedName("Unit")
        lateinit var unit: String
        @SerializedName("UnitType")
        lateinit var unitType: String

    }

class TemperatureValues {
    @SerializedName("Minimum")
    lateinit var minimum:Temperature

    @SerializedName("Maximum")
    lateinit var maximum:Temperature

}


    class Period {

        @SerializedName("Icon")
        lateinit var icon: String

        @SerializedName("IconPhrase")
        lateinit var iconPhrase: String

    }

    fun getMaxTempC():Float{
        return this.temperatureValues.maximum.value
    }


    fun getMinTempC():Float{
        return this.temperatureValues.minimum.value
    }


    fun getAvgTempC():Int{

        return ((getMinTempC()+getMaxTempC())/2).roundToInt()
    }


    fun getAvgTempF():Int{

        return ((getAvgTempC()*1.8f)+32f).roundToInt()
    }


    fun getMaxTempF():Int{
        return ((this.temperatureValues.maximum.value*1.8f)+32f).roundToInt()
    }


    fun getMinTempF():Int{
        return ((this.temperatureValues.minimum.value*1.8f)+32f).roundToInt()
    }




}