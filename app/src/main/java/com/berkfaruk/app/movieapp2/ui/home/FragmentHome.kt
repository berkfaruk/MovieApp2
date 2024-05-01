package com.berkfaruk.app.movieapp2.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkfaruk.app.movieapp2.BaseFragment
import com.berkfaruk.app.movieapp2.MainActivity
import com.berkfaruk.app.movieapp2.R
import com.berkfaruk.app.movieapp2.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentHome : BaseFragment<FragmentHomeBinding>() {

    private lateinit var movieAdapter: MovieAdapter
    private val viewModel by viewModels<HomeViewModel>()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding? {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = movieAdapter
        observeViewModel()

        binding.searchText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.getMovieList(s.toString())
            }

        })

        viewModel.getMovieListFromDatabase()

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.recyclerView.visibility = View.GONE
            binding.fragmentHomeProgressBar.visibility = View.VISIBLE
            viewModel.refreshData()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        (activity as MainActivity).supportActionBar?.title = getString(R.string.toolbarTitle)




    }

    private fun observeViewModel(){
        viewModel.data.observe(viewLifecycleOwner) {result ->
            Log.d("DataResponse", "observeViewModel: ${result}")
            movieAdapter.setData(result)

        }
        viewModel.isLoading.observe(viewLifecycleOwner){ loading ->
            Log.d("DataResponse", "observeViewModel: ${loading}")
            binding.fragmentHomeProgressBar.isVisible = loading
            binding.recyclerView.isVisible = !loading
        }
        viewModel.error.observe(viewLifecycleOwner){ error ->
            Log.d("DataResponse", "observeViewModel: ${error}")

        }

        viewModel.roomData.observe(viewLifecycleOwner) {result ->

            Log.d("RoomDataResponse", "observeViewModel: $result")


        }
    }

}