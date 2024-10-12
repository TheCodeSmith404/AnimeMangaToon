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
    private var articles: MutableList<Article> = mutableListOf()
    init {
        setHasStableIds(true)
    }

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
        fun updateFavoriteIcon(isFav: Boolean) {
            binding.favButton.isSelected = isFav
            val iconRes = if (isFav) R.drawable.ic_star_24 else R.drawable.ic_star_outline_24
            binding.favButton.setImageResource(iconRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            // Full binding if no payload is provided
            holder.bind(articles[position], position)
        } else {
            // Partial update for favorite status
            val article = articles[position]
            holder.updateFavoriteIcon(article.isFav)
        }
    }
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        onBindViewHolder(holder, position, mutableListOf())
    }

    override fun getItemId(position: Int): Long = articles[position].id.toLong() // Ensure Article has a unique ID
    override fun setHasStableIds(hasStableIds: Boolean) = super.setHasStableIds(true)
    fun setArticles(articles: MutableList<Article>){
        this.articles=articles
        notifyDataSetChanged()
    }
    fun updateArticle(article: Article, position: Int) {
        articles[position] = article
        notifyItemChanged(position, "favorite_updated")
    }

    override fun getItemCount(): Int {
        return articles.size
    }
    private val drawableMap= mapOf(
        "solo_leveling_large.jpg" to R.drawable.solo_leveling_large,
        "tower_large.jpg" to R.drawable.tower_large,
        "estate_large.jpg" to R.drawable.estate_large,
        "returner_large.jpg" to R.drawable.returners_large,
        "god_large.jpg" to R.drawable.god_large)
}