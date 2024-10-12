package com.tcs.test.animemangatoon.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tcs.test.animemangatoon.data.Repository
import com.tcs.test.animemangatoon.data.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    fun getAllFavoritesArticles():LiveData<MutableList<Article>>{
        return repository.getAllFavArticles()
    }
    fun setFavorite(article: Article){
        viewModelScope.launch {
            repository.updateArticle(article)
        }
    }
}