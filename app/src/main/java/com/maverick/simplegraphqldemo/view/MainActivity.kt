package com.maverick.simplegraphqldemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.maverick.simplegraphqldemo.databinding.ActivityMainBinding
import com.maverick.simplegraphqldemo.domain.SimpleCountry
import com.maverick.simplegraphqldemo.utils.DataState
import com.maverick.simplegraphqldemo.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeObservers()

        viewModel.getCountryList()

    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is DataState.Success<List<SimpleCountry>> -> {
                    binding.tvCountries.text = dataState.data.toString()
                }
                is DataState.Error -> {
                    binding.tvCountries.text = "Error..."
                }
                is DataState.Loading -> {
                    binding.tvCountries.text = "Loading..."
                }
            }
        }
    }

}