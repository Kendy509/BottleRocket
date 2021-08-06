package com.dynamicdevz.bottlerocket.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dynamicdevz.bottlerocket.model.City
import com.dynamicdevz.bottlerocket.view.SearchFragment

class CityAdapter(
    var dataSet: List<City>,
    var listener: SearchFragment.OnSelectedCity?):
    RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    class CityViewHolder(private val view: View,
                         private val listener: SearchFragment.OnSelectedCity?):
        RecyclerView.ViewHolder(view){
        fun onBind(city: City){
            view.findViewById<TextView>(android.R.id.text1).text = city.name
            view.setOnClickListener {
                println("click event $city")
                listener?.selectedCity(city)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(LayoutInflater.from(parent.context).inflate(
            android.R.layout.simple_list_item_1,
            parent,
            false
        ), listener)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.onBind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}