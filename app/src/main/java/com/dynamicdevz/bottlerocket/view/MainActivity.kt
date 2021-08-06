package com.dynamicdevz.bottlerocket.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dynamicdevz.bottlerocket.R
import com.dynamicdevz.bottlerocket.databinding.ActivityMainBinding
import com.dynamicdevz.bottlerocket.di.DI
import com.dynamicdevz.bottlerocket.model.City
import com.dynamicdevz.bottlerocket.model.CityDetails
import com.dynamicdevz.bottlerocket.model.HourlyWeather
import com.dynamicdevz.bottlerocket.model.WeatherRepositoryImpl
import com.dynamicdevz.bottlerocket.util.getTodaysDay
import com.dynamicdevz.bottlerocket.util.isLoading
import com.dynamicdevz.bottlerocket.view.adapters.ForecastWeatherAdapter
import com.dynamicdevz.bottlerocket.view.adapters.WeeklyWeatherAdapter
import com.dynamicdevz.bottlerocket.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity(), WeeklyWeatherAdapter.OnSelectedDay,
    SearchFragment.OnSelectedCity {

    private val SEARCH_FRAGMENT_TAG: String = "SearchFragment_TAG"
    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by lazy {
        DI.provideViewModel(WeatherViewModel::class.java)
    }
    private val hourlyAdapter: ForecastWeatherAdapter by lazy {
        DI.provideForeCastAdapter()
    }
    private val weeklyAdapter: WeeklyWeatherAdapter by lazy {
        DI.provideWeeklyAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initObservation()
        weatherViewModel.searchCity(4062577)
    }

    private fun initObservation() {
        weatherViewModel.weather.observe(this) {
            when (it) {
                is WeatherRepositoryImpl.CityDetailsState.Response ->
                    updateUI(it.cityDetails)
                WeatherRepositoryImpl.CityDetailsState.Loading ->
                    isLoading(true)
                is WeatherRepositoryImpl.CityDetailsState.Error ->
                    showError(it.errorMessage)
            }
        }
        weatherViewModel.hourly.observe(this) {
            updateHourlyAdapter(it)
        }
    }

    private fun updateHourlyAdapter(dataset: List<HourlyWeather>) {
        hourlyAdapter.dataset = dataset
        hourlyAdapter.notifyDataSetChanged()
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun isLoading(loading: Boolean) {
        binding.progressBar.isLoading(loading)
    }

    //accordingly of the day position
    private fun updateUI(cityDetails: CityDetails) {
        binding.progressBar.isLoading(false)

        binding.cityNameTv.text = cityDetails.city.name
        binding.dateTv.text = cityDetails.city.timezone.getTodaysDay()
        binding.temperatureTv.text = getString(
            R.string.weekly_temp_ph,
            cityDetails.weather.days[0].hourlyWeatherList[0].temperature
        )
        weeklyAdapter.listener = this
        weeklyAdapter.dataset = cityDetails.weather.days
        weeklyAdapter.notifyDataSetChanged()
    }

    private fun initViews() {
        binding.daysOfWeekRecyclerview.adapter = weeklyAdapter
        binding.daysOfWeekRecyclerview.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        binding.dayDetailsRecyclerview.adapter = hourlyAdapter
        binding.dayDetailsRecyclerview.layoutManager =
            LinearLayoutManager(this)
        binding.searchCity.setOnClickListener { openSearchFragment() }
    }

    private fun openSearchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SearchFragment(),SEARCH_FRAGMENT_TAG)
            .commit()
    }

    /**
     * User select the day, now filter the HourlyDay
     */
    override fun selectedDay(position: Int) {
        weatherViewModel.filterByDay(position)
    }

    override fun selectedCity(city: City) {
        supportFragmentManager.findFragmentByTag(SEARCH_FRAGMENT_TAG)?.let {
            supportFragmentManager.beginTransaction().remove(
                it
            ).commit()
        }
        weatherViewModel.searchCity(city.geonameid)
    }
}