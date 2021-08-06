package com.dynamicdevz.bottlerocket.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentActivity
import com.dynamicdevz.bottlerocket.R
import com.dynamicdevz.bottlerocket.view.SearchFragment
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun ProgressBar.isLoading(loading: Boolean){
    if (loading)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}

fun ImageView.processWeatherType(weatherType: String){
    this.setImageDrawable(when (weatherType) {
        "sunny" -> ResourcesCompat.getDrawable(
            this.context.resources,
            R.drawable.ic_icon_weather_active_ic_sunny_active,
            null
        )
        "cloudy" -> ResourcesCompat.getDrawable(
            this.context.resources,
            R.drawable.ic_icon_weather_active_ic_cloudy_active,
            null
        )
        "heavyRain" -> ResourcesCompat.getDrawable(
            this.context.resources,
            R.drawable.ic_icon_weather_active_ic_heavy_rain_active,
            null
        )
        "lightRain" -> ResourcesCompat.getDrawable(
            this.context.resources,
            R.drawable.ic_icon_weather_active_ic_light_rain_active,
            null
        )
        "snowSleet" -> ResourcesCompat.getDrawable(
            this.context.resources,
            R.drawable.ic_icon_weather_active_ic_snow_sleet_active,
            null
        )
        else ->
            ResourcesCompat.getDrawable(
                this.context.resources,
                R.drawable.ic_icon_weather_active_ic_partly_cloudy_active,
                null
            )
    })
}

fun FragmentActivity.closeSearchFragment(searchFragment: SearchFragment){
    this.supportFragmentManager.beginTransaction().remove(searchFragment).commit()
}

fun String.getTodaysDay(): String? {
    val date = Date()
    /* Specifying the format */
    val requiredFormat: DateFormat = SimpleDateFormat("EEEEEE")
    /* Setting the Timezone */requiredFormat.timeZone = TimeZone.getTimeZone(this)
    /* Picking the day value in the required Format */
    return requiredFormat.format(date).uppercase(Locale.getDefault())
}