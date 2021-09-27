package com.example.testmulti.articlelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ArticleModel
import com.example.testmulti.R
import com.example.testmulti.databinding.ItemArticleBinding


class ArticleViewHolder(
    private val binding: ItemArticleBinding,
    private val onArticleClick: ((ArticleModel) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.articleCard.setOnClickListener {
            onArticleClick?.invoke(binding.article!!)
        }
    }

    fun bind(data: ArticleModel) {
        binding.article = data
        binding.executePendingBindings()
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onArticleClick: ((ArticleModel) -> Unit)?
        ): ArticleViewHolder {
            val binding = DataBindingUtil.inflate<ItemArticleBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_article,
                parent,
                false
            )
            return ArticleViewHolder(
                binding,
                onArticleClick
            )
        }
    }


}