package com.example.testmulti.articlelist

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ArticleModel


class ArticleAdapter :
    RecyclerView.Adapter<ArticleViewHolder>() {

    private val articles: ArrayList<ArticleModel> = arrayListOf()
    var onArticleClick: ((ArticleModel) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setArticleList(articleList: List<ArticleModel>) {
        this.articles.apply {
            clear()
            addAll(articleList)
        }
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ArticleViewHolder.create(
            parent,
            onArticleClick
        )

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        return holder.bind(articles[position])
    }
}