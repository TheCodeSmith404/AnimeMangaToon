package com.tcs.test.animemangatoon.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tcs.test.animemangatoon.data.Repository
import com.tcs.test.animemangatoon.data.model.Article
import com.tcs.test.animemangatoon.utils.Articles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getAllArticles():LiveData<List<Article>>{
        return repository.getAllArticles()
    }
    fun setFavorite(article:Article){
        viewModelScope.launch {
            repository.updateArticle(article)
        }
    }
}