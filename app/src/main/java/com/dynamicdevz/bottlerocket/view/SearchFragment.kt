package com.dynamicdevz.bottlerocket.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dynamicdevz.bottlerocket.databinding.CitySearchLayoutBinding
import com.dynamicdevz.bottlerocket.di.DI
import com.dynamicdevz.bottlerocket.model.City
import com.dynamicdevz.bottlerocket.model.CitySearchResponse
import com.dynamicdevz.bottlerocket.model.WeatherRepositoryImpl
import com.dynamicdevz.bottlerocket.util.closeSearchFragment
import com.dynamicdevz.bottlerocket.util.isLoading
import com.dynamicdevz.bottlerocket.view.adapters.CityAdapter
import com.dynamicdevz.bottlerocket.viewmodel.CityViewModel

class SearchFragment: Fragment() {

    interface OnSelectedCity{
        fun selectedCity(city: City)
    }

    private lateinit var binding: CitySearchLayoutBinding
    private lateinit var listener: OnSelectedCity

    private val cityViewModel: CityViewModel by lazy {
        DI.provideViewModel(CityViewModel::class.java)
    }

    private val adapter: CityAdapter by lazy{
        DI.provideCityAdapter()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when(context){
            is OnSelectedCity -> listener = context
            else -> throw ExceptionInInitializerError("Incorrect Host Activity")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = CitySearchLayoutBinding.inflate(inflater,container, false)
        initViews()
        initObservation()
        return binding.root
    }

    private fun initObservation() {
        cityViewModel.city.observe(viewLifecycleOwner){
            when(it){
                is WeatherRepositoryImpl.CityState.Response -> updateAdapter(it.cityList)
                is WeatherRepositoryImpl.CityState.Error -> showError(it.errorMessage)
                WeatherRepositoryImpl.CityState.Loading -> binding.progressBar.isLoading(true)
            }
        }
    }

    private fun initViews() {
        binding.closeFragment.setOnClickListener {
//            activity?.supportFragmentManager?.popBackStack()
//            activity?.onBackPressed()
            activity?.closeSearchFragment(this)
        }
        binding.tilCitySearch.editText?.setOnEditorActionListener { _, actionId, _ ->
             if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                 binding.tilCitySearch.editText?.text?.toString()?.let { city ->
                     cityViewModel.searchCities(city)
                     binding.progressBar.isLoading(true)
                     return@setOnEditorActionListener true
                 }
            }
            false
        }
        binding.cityList.adapter = adapter
        binding.cityList.layoutManager = LinearLayoutManager(context)
    }

    private fun updateAdapter(cityList: CitySearchResponse) {
        binding.progressBar.isLoading(false)
        adapter.listener = listener
        adapter.dataSet = cityList.cities
        adapter.notifyDataSetChanged()
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

}