package com.berkfaruk.app.movieapp2.ui.home

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkfaruk.app.movieapp2.MovieAdapter
import com.berkfaruk.app.movieapp2.R
import com.berkfaruk.app.movieapp2.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext

@AndroidEntryPoint
class FragmentHome : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieAdapter

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieList("bat")
        observeViewModel()

    }

    private fun observeViewModel(){
        viewModel.data.observe(viewLifecycleOwner) {result ->
            Log.d("DataResponse", "observeViewModel: ${result}")

            movieAdapter = MovieAdapter(result!!)
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = movieAdapter
        }
        viewModel.isLoading.observe(viewLifecycleOwner){ loading ->
            Log.d("DataResponse", "observeViewModel: ${loading}")
        }
        viewModel.error.observe(viewLifecycleOwner){ error ->
            Log.d("DataResponse", "observeViewModel: ${error}")
        }
    }
}