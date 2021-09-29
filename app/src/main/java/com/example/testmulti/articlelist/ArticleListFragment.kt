package com.example.testmulti.articlelist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.common.base.BaseFragment
import com.example.common.extension.observe
import com.example.domain.model.ArticleModel
import com.example.testmulti.R
import com.example.testmulti.databinding.ArticleListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleListFragment :
    BaseFragment<ArticleListViewModel, ArticleListFragmentBinding>(R.layout.article_list_fragment) {

    private val articleVM: ArticleListViewModel by viewModels()
    private val articlesAdapter by lazy { ArticleAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = articleVM
        setUpRecycler()
        observe(articleVM.articleListData, ::observeArticles)
        onArticleClick()
    }

    private fun onArticleClick() {
        articlesAdapter.onArticleClick = {
            articleVM.showMessage(it.title)
        }
    }

    private fun observeArticles(articleList: List<ArticleModel>?) {
        articleList?.let {
            articlesAdapter.setArticleList(articleList)
            binding.executePendingBindings()
        }
    }

    private fun setUpRecycler() {
        binding.articlesRecycler.apply {
            adapter = articlesAdapter
            setHasFixedSize(true)
        }
    }

    override fun getViewModel() = articleVM
}

