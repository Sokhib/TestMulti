package com.example.testmulti.articlelist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.common.base.BaseFragment
import com.example.testmulti.R
import com.example.testmulti.TestIntentActivity
import com.example.testmulti.databinding.ArticleListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview


@FlowPreview
@AndroidEntryPoint
class ArticleListFragment :
    BaseFragment<ArticleListViewModel, ArticleListFragmentBinding>(R.layout.article_list_fragment) {

    private val articleVM: ArticleListViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.viewmodel = articleVM

        binding!!.circleView.setOnClickListener {
            startActivity(Intent(requireActivity(), TestIntentActivity::class.java))
        }
        binding!!.sliderView.setOnClickListener {
        }
    }


    override fun getViewModel() = articleVM

}

