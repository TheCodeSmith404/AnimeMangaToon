package com.tcs.test.animemangatoon.ui.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tcs.test.animemangatoon.R
import com.tcs.test.animemangatoon.data.model.Article
import com.tcs.test.animemangatoon.databinding.FavoriteItemBinding
import com.tcs.test.animemangatoon.databinding.ListItemBinding
import com.tcs.test.animemangatoon.ui.home.adapter.HomeRecycleViewAdapter

class FavoritesAdapter(private val onClick:(Article,Int)->Unit):  RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {
    private var articles: MutableList<Article> = mutableListOf()
    inner class ViewHolder(private val binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article,position: Int) {
            binding.titleTv.text = article.name
            Glide.with(binding.imageView2.context)
                .load(AppCompatResources.getDrawable(binding.imageView2.context,drawableMap[article.imgSmall]?: R.drawable.solo_leveling_small))
                .into(binding.imageView2)
            binding.imageButton.isSelected=true
            binding.imageButton.setOnClickListener {
                article.isFav=!article.isFav
                articles.removeAt(position)
                onClick(article,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=FavoriteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article=articles[position]
        holder.bind(article,position)
    }
    fun setArticles(articles: MutableList<Article>){
        this.articles=articles
        notifyDataSetChanged()
    }
    private val drawableMap= mapOf(
        "solo_leveling_small.jpg" to R.drawable.solo_leveling_small,
        "tower_small.jpg" to R.drawable.tower_small,
        "estate_small.jpg" to R.drawable.estate_small,
        "returner_small.jpg" to R.drawable.returners_small,
        "god_small.jpg" to R.drawable.god_small)

}