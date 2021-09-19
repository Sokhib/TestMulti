package com.example.testmulti.articlelist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.common.base.BaseFragment
import com.example.common.extension.observe
import com.example.testmulti.R
import com.example.testmulti.databinding.ArticleListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleListFragment :
    BaseFragment<ArticleListViewModel, ArticleListFragmentBinding>(R.layout.article_list_fragment) {

    private val articleVM: ArticleListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.viewmodel = articleVM
        observe(articleVM.state, ::setCircleState)
    }


    override fun getViewModel() = articleVM
    private fun setCircleState(state: Boolean) {
        if (state) binding!!.circleView.setChecked()
    }
}

