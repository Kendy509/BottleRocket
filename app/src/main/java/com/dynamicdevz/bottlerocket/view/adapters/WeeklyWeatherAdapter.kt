package com.dynamicdevz.bottlerocket.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dynamicdevz.bottlerocket.R
import com.dynamicdevz.bottlerocket.databinding.WeeklyWeatherLayoutBinding
import com.dynamicdevz.bottlerocket.model.Day
import com.dynamicdevz.bottlerocket.util.processWeatherType

class WeeklyWeatherAdapter(var dataset: List<Day>, var listener: OnSelectedDay? = null) :
    RecyclerView.Adapter<WeeklyWeatherAdapter.WeeklyWeatherViewHolder>() {

    interface OnSelectedDay {
        fun selectedDay(position: Int)
    }

    class WeeklyWeatherViewHolder(
        private val binding: WeeklyWeatherLayoutBinding,
        private val listener: OnSelectedDay?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(dataitem: Day) {
            binding.temp.text = processWeeklyTemp(dataitem.high)
            binding.day.text = processWeeklyDay(dataitem.dayOfTheWeek)
            binding.weather.processWeatherType(dataitem.weatherType)
            binding.root.setOnClickListener { listener?.selectedDay(adapterPosition) }
        }

        private fun processWeeklyDay(day: Int): String {
            return binding.root.context.resources.getStringArray(R.array.week_days)[day]
        }

        private fun processWeeklyTemp(temp: Int) =
            binding.root.context.getString(R.string.weekly_temp_ph, temp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyWeatherViewHolder {
        return WeeklyWeatherViewHolder(
            WeeklyWeatherLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: WeeklyWeatherViewHolder, position: Int) {
        holder.onBind(dataset[position])
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}