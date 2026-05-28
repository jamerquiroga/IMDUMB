package com.jquiroga.imdumb.features.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.jquiroga.imdumb.databinding.ActivitySplashBinding
import com.jquiroga.imdumb.features.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity(), SplashContract.View {

    private val viewBinding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(viewBinding.root)

        presenter.attach(this)
        presenter.loadInitialConfig()
    }

    override fun showLoading() {
        viewBinding.loadingOverlay.isVisible = true
        viewBinding.progressBar.isVisible = true
    }

    override fun hideLoading() {
        viewBinding.loadingOverlay.isVisible = false
        viewBinding.progressBar.isVisible = false
    }

    override fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }
}