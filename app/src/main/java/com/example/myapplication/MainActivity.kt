package com.example.myapplication

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.api.ApiService
import com.example.myapplication.api.retrofit
import com.example.myapplication.responses.WeatherInfo
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class MainActivity : AppCompatActivity() {


    val FUnit: String = "°F"
    val CUnit: String = "°C"
    var isCelsius: Boolean = false

    var weatherInfo5: WeatherInfo? = null
    lateinit var callWeatherInfo: Call<WeatherInfo>

    var locationID: Int = 298740
    var apiKey: String = "TpXJFQ2vKSD4YhG3cL19fsGgpgcQZzMR"
    var metric: Boolean = true

var icheck=0
    var myPreferences:AppPreferences?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        myPreferences= AppPreferences(baseContext)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        isCelsius=myPreferences!!.isCelsius

        locationID=myPreferences!!.lastCityName



sch_unit.setOnCheckedChangeListener { sch_Unit, isChecked ->
    if(isChecked){
        isCelsius=true
    }
    else
    {isCelsius=false}

    setTemperatures(isCelsius)

}


        refresh5DayWeather(locationID, apiKey, metric)



        ArrayAdapter.createFromResource(
            this,

            R.array.cities_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spin_cities.adapter = adapter
        }



        class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                locationID=codes[pos]
                if(icheck!=0) {
                    refresh5DayWeather(locationID, apiKey, metric);
                    refreshUIpics()
                    setTemperatures(isCelsius)
                }
                icheck++
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

spin_cities.onItemSelectedListener=SpinnerActivity()



    }

    public fun refresh5DayWeather(locationID: Int, apiKey: String, metric: Boolean) {

        var apiService: ApiService = retrofit.create(ApiService::class.java)
        callWeatherInfo = apiService.get5DayWeather(locationID, apiKey, metric.toString())




        callWeatherInfo.enqueue(object : Callback<WeatherInfo> {
            override fun onFailure(call: Call<WeatherInfo>?, t: Throwable?) {
                Toast.makeText(applicationContext, "Data retrieval failed", Toast.LENGTH_LONG).show()
                Log.d("blu3",t.toString())
            }

            override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                Log.d(
                    "blu3",
                    response!!.message()
                )
                weatherInfo5 = response.body()
                refreshUIpics()
            }
        })


    }


    fun refreshUIpics() {


        Glide.with(applicationContext)
            .load("https://developer.accuweather.com/sites/default/files/" +formatOneFigure( weatherInfo5!!.getDay(0).day.icon )+ "-s.png")
            .apply(RequestOptions().override(200, 200))
            .into(iv_day)

        Glide.with(applicationContext)
            .load("https://developer.accuweather.com/sites/default/files/" +formatOneFigure( weatherInfo5!!.getDay(0).night.icon )+ "-s.png")
            .apply(RequestOptions().override(200, 200))
            .into(iv_night)

        Glide.with(applicationContext)
            .load("https://developer.accuweather.com/sites/default/files/" +formatOneFigure( weatherInfo5!!.getDay(1).day.icon) + "-s.png")
            .apply(RequestOptions().override(200, 200))
            .into(iv_day1)

        Glide.with(applicationContext)
            .load("https://developer.accuweather.com/sites/default/files/" + formatOneFigure(weatherInfo5!!.getDay(2).day.icon) + "-s.png")
            .apply(RequestOptions().override(200, 200))
            .into(iv_day2)

        Glide.with(applicationContext)
            .load("https://developer.accuweather.com/sites/default/files/" +formatOneFigure( weatherInfo5!!.getDay(3).day.icon )+ "-s.png")
            .apply(RequestOptions().override(200, 200))
            .into(iv_day3)

        Glide.with(applicationContext)
            .load("https://developer.accuweather.com/sites/default/files/" +formatOneFigure( weatherInfo5!!.getDay(4).day.icon) + "-s.png")
            .apply(RequestOptions().override(200, 200))
            .into(iv_day4)



        tv_night_txt.setText(weatherInfo5!!.getDay(0).night.iconPhrase);
        tv_day_txt.setText(weatherInfo5!!.getDay(0).day.iconPhrase);



        setTemperatures(isCelsius)


    }

    fun setTemperatures(isCelsius: Boolean) {

        if (isCelsius) {
            tv_min_temp_value.setText(weatherInfo5!!.getDay(0).getMinTempC().toString() + CUnit)
            tv_max_temp_value.setText(weatherInfo5!!.getDay(0).getMaxTempC().toString() + CUnit)


            tv_avg_temp_value1.setText(weatherInfo5!!.getDay(1).getAvgTempC().toString() + CUnit)
            tv_avg_temp_value2.setText(weatherInfo5!!.getDay(2).getAvgTempC().toString() + CUnit)
            tv_avg_temp_value3.setText(weatherInfo5!!.getDay(3).getAvgTempC().toString() + CUnit)
            tv_avg_temp_value4.setText(weatherInfo5!!.getDay(4).getAvgTempC().toString() + CUnit)
        } else {

            tv_min_temp_value.setText(weatherInfo5!!.getDay(0).getMinTempF().toString() + FUnit)
            tv_max_temp_value.setText(weatherInfo5!!.getDay(0).getMaxTempF().toString() + FUnit)


            tv_avg_temp_value1.setText(weatherInfo5!!.getDay(1).getAvgTempF().toString() + FUnit)
            tv_avg_temp_value2.setText(weatherInfo5!!.getDay(2).getAvgTempF().toString() + FUnit)
            tv_avg_temp_value3.setText(weatherInfo5!!.getDay(3).getAvgTempF().toString() + FUnit)
            tv_avg_temp_value4.setText(weatherInfo5!!.getDay(4).getAvgTempF().toString() + FUnit)

        }

    }

private  fun formatOneFigure(icon:String):String{
        if(icon.length==1)
            return "0"+icon
    else return icon

    }

    var cities=arrayOf("New York","London","Berlin","Paris","Madrid","Prishtina")
    var codes=arrayOf(349727 , 328328 , 178087,623 , 308526, 298740)

    private fun cityCode(city:String):Int{
for(x in 0..codes.size-1)
    if(cities[x]==city)
        return codes[x]
return 298740;
    }


    override fun onStop() {
        super.onStop()
        myPreferences!!.isCelsius=isCelsius
        myPreferences!!.lastCityName=locationID

    }


}



