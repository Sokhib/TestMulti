package com.example.testmulti.articlelist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.base.BaseFragment
import com.example.common.extension.observe
import com.example.testmulti.R
import com.example.testmulti.RecordController
import com.example.testmulti.TestIntentActivity
import com.example.testmulti.databinding.ArticleListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber


@AndroidEntryPoint
class ArticleListFragment :
    BaseFragment<ArticleListViewModel, ArticleListFragmentBinding>(R.layout.article_list_fragment) {

    private val articleVM: ArticleListViewModel by viewModels()
    private val recordController by lazy { RecordController(requireActivity()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.viewmodel = articleVM
        observe(articleVM.state, ::setCircleState)

        binding!!.circleView.setOnClickListener {
            startActivity(Intent(requireActivity(), TestIntentActivity::class.java))
        }
        requestPermission()
        binding!!.sliderView.setOnClickListener {
            onButtonClicked()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(android.Manifest.permission.RECORD_AUDIO),
            101,
        )
    }

    private fun onButtonClicked() {
        lifecycleScope.launch(Dispatchers.Default) {
            if (recordController.isAudioRecording()) {
                recordController.stop()

            } else {
                recordController.start()
                repeat(100_000) {
                    delay(100)
                    val volume = recordController.getVolume()
                    withContext(Dispatchers.Main) {
                        handleVolume(volume)
                    }
                }
            }

        }

    }

    private fun handleVolume(volume: Int) {
        Timber.d("Volume: $volume")
        val scale = 10.0.coerceAtMost(volume / MAX_RECORD_AMPLITUDE + 1.0f).toFloat()
        binding!!.circleView.animate()
            .scaleX(scale)
            .scaleY(scale)
            .setInterpolator(interpolator)
            .duration = VOLUME_UPDATE_DURATION
    }

    override fun getViewModel() = articleVM
    private fun setCircleState(state: Boolean) {
        if (state) binding!!.circleView.setChecked()
    }

    private companion object {
        private const val MAX_RECORD_AMPLITUDE = 32768.0
        private const val VOLUME_UPDATE_DURATION = 100L
        private val interpolator = OvershootInterpolator()
    }
}

