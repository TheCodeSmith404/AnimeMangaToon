package com.tcs.test.animemangatoon.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tcs.test.animemangatoon.data.Repository
import com.tcs.test.animemangatoon.data.model.Article
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    suspend fun getArticleById(id:Int): Article {
        return repository.getArticleById(id)
    }
    fun setFavorite(article: Article){
        viewModelScope.launch {
            repository.updateArticle(article)
        }
    }
    fun setRating(article: Article){
        viewModelScope.launch {
            repository.updateArticle(article)
        }
    }
}