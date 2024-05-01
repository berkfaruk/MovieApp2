package com.berkfaruk.app.movieapp2.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.berkfaruk.app.movieapp2.BaseFragment
import com.berkfaruk.app.movieapp2.MainActivity
import com.berkfaruk.app.movieapp2.R
import com.berkfaruk.app.movieapp2.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val viewModel by viewModels<DetailViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity).supportActionBar?.title = "Movie Details"
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()

        arguments?.let {
            val itemImdbId = DetailFragmentArgs.fromBundle(it).imdbId
            viewModel.getMovieDetail(itemImdbId)
        }

    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding? {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    private fun observeViewModel() {
        viewModel.data.observe(viewLifecycleOwner) { movies ->
            movies?.let {
                binding.moviesDetail = it
            }
        }


        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            binding.detailFragmentProgressBar.isVisible = loading
            binding.scrollView.isVisible = !loading
        }
    }

}