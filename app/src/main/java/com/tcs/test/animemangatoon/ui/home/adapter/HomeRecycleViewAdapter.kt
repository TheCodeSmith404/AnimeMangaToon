package com.tcs.test.animemangatoon.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tcs.test.animemangatoon.R
import com.tcs.test.animemangatoon.data.model.Article
import com.tcs.test.animemangatoon.databinding.ListItemBinding
import com.tcs.test.animemangatoon.ui.home.OnArticleItemClickListener


class HomeRecycleViewAdapter(private val clickListener:OnArticleItemClickListener) : RecyclerView.Adapter<HomeRecycleViewAdapter.ArticleViewHolder>() {
    private var articles: List<Article> = emptyList()

    inner class ArticleViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article,position: Int) {
            binding.titleTv.text = article.name
            binding.writerTv.text = article.writer
            binding.studioTv.text = article.art
            binding.readsTv.text = article.reads
            binding.descriptionTv.text = article.description
            binding.favButton.isSelected = article.isFav
            Glide.with(binding.imageView.context)
                .load(AppCompatResources.getDrawable(binding.imageView.context,drawableMap[article.img]?:R.drawable.solo_leveling_large))
                .into(binding.imageView)
            binding.favButton.setOnClickListener {
                article.isFav=!article.isFav
                clickListener.onImageButtonClicked(article,position)
            }
            binding.root.setOnClickListener{
                clickListener.onAnswerClicked(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article,position)
    }

    fun setArticles(articles: List<Article>){
        this.articles=articles
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return articles.size
    }
    private val drawableMap= mapOf("solo_leveling.jpg" to R.drawable.solo_leveling_large)
}