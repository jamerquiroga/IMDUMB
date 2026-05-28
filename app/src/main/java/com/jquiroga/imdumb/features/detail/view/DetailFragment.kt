package com.jquiroga.imdumb.features.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.jquiroga.imdumb.R
import com.jquiroga.imdumb.core.common.HorizontalSpacingDecoration
import com.jquiroga.imdumb.core.platform.BaseFragment
import com.jquiroga.imdumb.databinding.FragmentDetailBinding
import com.jquiroga.imdumb.features.detail.adapter.ActorAdapter
import com.jquiroga.imdumb.features.detail.adapter.ImageCarouselAdapter
import com.jquiroga.imdumb.features.detail.model.MovieDetailUiModel
import com.jquiroga.imdumb.features.recommend.RecommendBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class DetailFragment : BaseFragment(), DetailContract.View {

    private var _viewBinding: FragmentDetailBinding? = null

    private val viewBinding get() = _viewBinding!!

    private val args: DetailFragmentArgs by navArgs()

    @Inject
    lateinit var presenter: DetailPresenter

    private val imageCarouselAdapter = ImageCarouselAdapter()

    private val actorAdapter = ActorAdapter()

    private var tabMediator: TabLayoutMediator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun initUI() {
        setupViews()
        presenter.attach(this)
        presenter.loadMovieDetail(args.movieId)
        viewBinding.buttonRecommend.setOnClickListener {
            presenter.onRecommendClicked()
        }
        viewBinding.buttonRetry.setOnClickListener {
            presenter.onRetry(args.movieId)
        }
    }

    private fun setupViews() {
        viewBinding.viewPagerImages.adapter = imageCarouselAdapter
        viewBinding.recyclerActors.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = actorAdapter
            val spacing = resources.getDimensionPixelSize(R.dimen.spacing_small)
            addItemDecoration(HorizontalSpacingDecoration(spacing))
        }
    }

    override fun detachPresenter() {
        presenter.detach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabMediator?.detach()
        tabMediator = null
        _viewBinding = null
    }

    override fun showLoading() {
        viewBinding.run {
            loadingOverlay.isVisible = true
            progressBar.isVisible = true
            contentScroll.isVisible = false
            layoutError.isVisible = false
            buttonRecommend.isEnabled = false
        }
    }

    override fun hideLoading() {
        viewBinding.run {
            loadingOverlay.isVisible = false
            progressBar.isVisible = false
            buttonRecommend.isEnabled = true
        }
    }

    override fun showContent(detail: MovieDetailUiModel) {
        viewBinding.run {
            contentScroll.isVisible = true
            layoutError.isVisible = false

            textTitle.text = detail.title
            textRating.text = detail.rating
            textOverview.text = detail.overview

            val images = detail.imageUrls
            imageCarouselAdapter.submitList(images)
            tabMediator?.detach()
            tabMediator = TabLayoutMediator(tabIndicator, viewPagerImages) { _, _ -> }
            tabMediator?.attach()
            tabIndicator.isVisible = images.size > 1

            actorAdapter.submitList(detail.cast)
            textCastLabel.isVisible = detail.cast.isNotEmpty()
            recyclerActors.isVisible = detail.cast.isNotEmpty()
        }
    }

    override fun showError(message: String) {
        viewBinding.run {
            layoutError.isVisible = true
            textError.text = message
            contentScroll.isVisible = false
            loadingOverlay.isVisible = false
        }
    }

    override fun openRecommendBottomSheet(detail: MovieDetailUiModel) {
        RecommendBottomSheetFragment.newInstance(detail.title, detail.rating)
            .show(parentFragmentManager, RecommendBottomSheetFragment.TAG)
    }
}