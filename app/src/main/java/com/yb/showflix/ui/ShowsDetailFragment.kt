package com.yb.showflix.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.yb.showflix.R
import com.yb.showflix.core.extension.load
import com.yb.showflix.databinding.FragmentShowsDetailBinding
import com.yb.showflix.model.detail.ShowDetail
import com.yb.showflix.repository.ResponseState
import com.yb.showflix.ui.viewmodel.ShowsDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ShowsDetailFragment : Fragment(R.layout.fragment_shows_detail) {

    private val _showsDetailViewModel : ShowsDetailViewModel by activityViewModels()
    private lateinit var _binding: FragmentShowsDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShowsDetailBinding.bind(view)
        initObserver()
    }

    private fun initObserver(){
        _showsDetailViewModel.showDetail.observe(viewLifecycleOwner){
            lifecycleScope.launchWhenStarted {
                it.collect { result ->
                    when(result){
                        is ResponseState.Success -> {
                           val showDetail = result.data as ShowDetail
                            this@ShowsDetailFragment.bindView(showDetail)
                        }
                        is ResponseState.Error -> {
                            Log.e("ShowsDetailFragment",result.message)
                        }
                    }
                }
            }
        }
    }

    private fun bindView(detail: ShowDetail){
        _binding.showPoster.load(detail.Poster)
        _binding.showTitle.text = detail.Title
        _binding.showRated.text = detail.Rated
        _binding.plotTitle.text = detail.Plot
        _binding.crewTitle.text = getCrewInfo(detail)
    }

    private fun getCrewInfo(detail:ShowDetail): String{
        return "Actors: ${detail.Actors} \n" +
                "Director: ${detail.Director} \n" +
                "Production : ${detail.Production} \n" +
                "Writer: ${detail.Writer} \n" +
                "Duration: ${detail.Runtime} \n"+
                "Year: ${detail.Year} \n"+
                "DVD Release: ${detail.DVD} \n"+
                "Language: ${detail.Language} \n"+
                "Country: ${detail.Country} \n"+
                "Box Office: ${detail.BoxOffice} \n"+
                "Rating: ${detail.imdbRating} \n" +
                "Metascore: ${detail.Metascore} \n"+
                "Website: ${detail.Website} \n"+
                "Genre: ${detail.Genre}\n"+
                "Type: ${detail.Type}"
    }
}