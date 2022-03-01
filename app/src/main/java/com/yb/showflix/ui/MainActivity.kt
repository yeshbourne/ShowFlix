package com.yb.showflix.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.yb.showflix.R
import com.yb.showflix.databinding.ActivityMainBinding
import com.yb.showflix.model.search.ShowsResponse
import com.yb.showflix.ui.viewmodel.ShowsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val _showsViewModel: ShowsViewModel by viewModels()
    private lateinit var _binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        initObservers()
    }

    private fun initObservers(){
        _binding.searchButton.setOnClickListener {
            Log.d("MainActivity", "imageButtonClick")
            _binding.searchText.let {
                if(it.length() > 1) _showsViewModel.getShows(_binding.searchText.text.toString()) else Log.d("","")
            }
        }
    }
}