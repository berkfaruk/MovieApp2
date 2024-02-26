package com.berkfaruk.app.movieapp2.ui.home

import android.os.Bundle

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.berkfaruk.app.movieapp2.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentHome : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieList("bat")
    }

    private fun observeViewModel(){
        viewModel.data.observe(viewLifecycleOwner){ result ->

        }
        viewModel.isLoading.observe(viewLifecycleOwner){ loading ->

        }
        viewModel.error.observe(viewLifecycleOwner){ error ->

        }
    }
}