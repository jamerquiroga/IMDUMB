package com.jquiroga.imdumb.features.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jquiroga.imdumb.core.platform.BaseFragment
import com.jquiroga.imdumb.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment() {

    private var _viewBinding: FragmentHomeBinding? = null

    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun initUI() {

    }

    override fun detachPresenter() {

    }
}