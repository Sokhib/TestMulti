package com.example.testmulti.articlelist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.common.base.BaseFragment
import com.example.testmulti.R
import com.example.testmulti.databinding.ArticleListFragmentBinding

class ArticleListFragment :
    BaseFragment<ArticleListViewModel, ArticleListFragmentBinding>(R.layout.article_list_fragment) {

    private val articleVM: ArticleListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.sampleText.setOnClickListener {
            articleVM.showMessage("Hey There")
        }

    }


    override fun getViewModel() = articleVM
}