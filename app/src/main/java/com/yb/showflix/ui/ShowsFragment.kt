package com.yb.showflix.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yb.showflix.R
import com.yb.showflix.databinding.FragmentShowsListBinding
import com.yb.showflix.model.search.Show
import com.yb.showflix.model.search.ShowsResponse
import com.yb.showflix.repository.ResponseState
import com.yb.showflix.ui.viewmodel.ShowsDetailViewModel
import com.yb.showflix.ui.viewmodel.ShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class ShowsFragment : Fragment(R.layout.fragment_shows_list), ShowItemRecyclerViewAdapter.ShowItemListener {
    private val _viewModel: ShowsViewModel by activityViewModels()
    private val _showsDetailViewModel : ShowsDetailViewModel by activityViewModels()

    private lateinit var _binding: FragmentShowsListBinding
    private lateinit var _recycleView: RecyclerView

    private val _navController: NavController by lazy {
        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShowsListBinding.bind(view)

        initRecycleView()
        initObservers()
    }

    private fun initRecycleView() {
        _recycleView = _binding.list
        _recycleView.layoutManager = LinearLayoutManager(context)
//        _recycleView.addDecoration()
    }

    private fun initObservers() {
        _viewModel.shows.observe(viewLifecycleOwner) {
            lifecycleScope.launchWhenStarted {
                it.collect { result ->
                    when (result) {
                        is ResponseState.Success -> {
                            val showsResponse = result.data as ShowsResponse
                            Log.d("Response", showsResponse.totalResults)
                            this@ShowsFragment.initAdapter(showsResponse.Search.sortedWith(compareByDescending {
                                it.Year
                            }))
                        }
                        is ResponseState.Error -> Log.e("Tag.API_TAG", result.message)
                    }
                }
            }
        }
    }


    private fun initAdapter(response: List<Show>) {
        _recycleView.adapter = ShowItemRecyclerViewAdapter(response, this)
    }

    override fun onClickItemListener(imdbId: String) {
        Log.d("ShowsFragment", imdbId)
        _showsDetailViewModel.getShowDetail(imdbId)
        _navController.navigate(ShowsFragmentDirections.actionShowsFragmentToShowsDetailFragment())
    }

}