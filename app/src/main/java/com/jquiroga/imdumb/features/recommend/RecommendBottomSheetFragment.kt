package com.jquiroga.imdumb.features.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.jquiroga.imdumb.R
import com.jquiroga.imdumb.databinding.BottomSheetRecommendBinding

class RecommendBottomSheetFragment : BottomSheetDialogFragment() {

    private var _viewBinding: BottomSheetRecommendBinding? = null

    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = BottomSheetRecommendBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = requireArguments().getString(ARG_TITLE).orEmpty()
        val rating = requireArguments().getString(ARG_RATING).orEmpty()

        viewBinding.run {
            textMovieTitle.text = title
            textMovieRating.text = getString(R.string.rating_format, rating)
            textSummary.text = getString(R.string.recommend_summary, title)

            buttonConfirm.setOnClickListener {
                dismiss()
                val message = getString(R.string.recommend_success, title)

                activity?.findViewById<View>(R.id.nav_host_fragment)?.let { anchor ->
                    Snackbar.make(anchor, message, Snackbar.LENGTH_LONG).show()
                }
            }
        }

        expandBottomSheet()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    private fun expandBottomSheet() {
        dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)?.let { sheet ->
            BottomSheetBehavior.from(sheet).apply {
                state = BottomSheetBehavior.STATE_EXPANDED
                skipCollapsed = true
            }
        }
    }

    companion object {
        const val TAG = "RecommendBottomSheet"
        private const val ARG_TITLE = "arg_title"
        private const val ARG_RATING = "arg_rating"

        fun newInstance(
            title: String,
            rating: String,
        ): RecommendBottomSheetFragment = RecommendBottomSheetFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_RATING, rating)
            }
        }
    }
}