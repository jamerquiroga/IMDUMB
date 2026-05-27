package com.jquiroga.imdumb.features.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jquiroga.imdumb.core.platform.BaseFragment
import com.jquiroga.imdumb.databinding.FragmentHomeBinding
import com.jquiroga.imdumb.features.home.adapter.CategoryAdapter
import com.jquiroga.imdumb.features.home.model.MovieCategoryUiModel
import com.jquiroga.imdumb.features.home.model.MovieUiModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment(), HomeContract.View {

    private var _viewBinding: FragmentHomeBinding? = null

    private val viewBinding get() = _viewBinding!!

    @Inject
    lateinit var presenter: HomePresenter

    private val sharedPool = RecyclerView.RecycledViewPool()

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun initUI() {
        setupRecyclerView()
        viewBinding.buttonRetry.setOnClickListener { presenter.onRetry() }
        presenter.attach(this)
        presenter.loadCategories()
    }

    override fun detachPresenter() {
        presenter.detach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    private fun setupRecyclerView() {
        categoryAdapter = CategoryAdapter(sharedPool) { movie ->
            presenter.onMovieClicked(movie)
        }
        viewBinding.recyclerCategories.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = categoryAdapter
            setHasFixedSize(true)
        }
    }

    override fun showLoading() {
        viewBinding.run {
            shimmerLayout.isVisible = true
            shimmerLayout.startShimmer()
            recyclerCategories.isVisible = false
            layoutError.isVisible = false
            layoutEmpty.isVisible = false
        }
    }

    override fun hideLoading() {
        viewBinding.shimmerLayout.stopShimmer()
        viewBinding.shimmerLayout.isVisible = false
    }

    override fun showCategories(categories: List<MovieCategoryUiModel>) {
        viewBinding.run {
            recyclerCategories.isVisible = true
            layoutError.isVisible = false
            layoutEmpty.isVisible = false
        }
        categoryAdapter.submitList(categories)
    }

    override fun showError(message: String) {
        viewBinding.run {
            layoutError.isVisible = true
            textError.text = message
            recyclerCategories.isVisible = false
            layoutEmpty.isVisible = false
        }
    }

    override fun showEmpty() {
        viewBinding.run {
            layoutEmpty.isVisible = true
            recyclerCategories.isVisible = false
            layoutError.isVisible = false
        }
    }

    override fun navigateToDetail(movie: MovieUiModel) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(
            movieId = movie.id
        )
        findNavController().navigate(action)
    }
}