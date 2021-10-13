package com.example.testmulti.articlelist

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.common.base.BaseFragment
import com.example.common.extension.observe
import com.example.testmulti.R
import com.example.testmulti.databinding.ArticleListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import timber.log.Timber


@FlowPreview
@AndroidEntryPoint
class ArticleListFragment :
    BaseFragment<ArticleListViewModel, ArticleListFragmentBinding>(R.layout.article_list_fragment) {

    private val articleVM: ArticleListViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.viewmodel = articleVM
        observe(articleVM.state, ::setCircleState)
        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    Timber.d("Permission is: $isGranted")
                } else {
                    Timber.d("Bhaijan look we need this permission")
                }
            }



        binding!!.circleView.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED -> {
                    Timber.d("Bhaijan permission is already granted. What are you doing...")
                }
                shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                    Timber.d("Bhaijan this is education UI for the permission")
                    requestPermissionLauncher.launch(
                        Manifest.permission.CAMERA
                    )
                }
                else -> {
                    requestPermissionLauncher.launch(
                        Manifest.permission.CAMERA
                    )

                }
            }
        }
    }


    override fun getViewModel() = articleVM
    private fun setCircleState(state: Boolean) {
        if (state) binding!!.circleView.setChecked()
    }
}

