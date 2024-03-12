package com.berkfaruk.app.movieapp2.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.berkfaruk.app.movieapp2.databinding.FragmentDetailBinding
import com.berkfaruk.app.movieapp2.utils.downloadFromUrl
import com.berkfaruk.app.movieapp2.utils.placeHolderProgressBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()

        arguments?.let {
            val itemImdbId = DetailFragmentArgs.fromBundle(it).imdbId
            viewModel.getMovieDetail(itemImdbId)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    private fun observeViewModel() {
        viewModel.data.observe(viewLifecycleOwner) { movies ->
            movies?.let {
                binding.movieDetailImage.downloadFromUrl(it.Poster, placeHolderProgressBar(binding.root.context))
                binding.movieDetailTitle.text = it.Title
                binding.movieDetailYear.text = it.Year
                binding.movieDetailRuntime.text = "Runtime: ${it.Runtime}"
                binding.movieDetailGenre.text = "Genre: ${it.Genre}"
                binding.movieDetailDirector.text = "Director: ${it.Director}"
                binding.movieDetailActors.text = "Actors: ${it.Actors}"
                binding.movieDetailImdbrate.text = "ImdbRating: ${it.imdbRating}"
                binding.movieDetailMetascore.text = "Metascore: ${it.Metascore}"
                binding.movieDetailPlot.text = "Plot: ${it.Plot}"


            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            binding.detailFragmentProgressBar.isVisible = loading
        }
    }

}