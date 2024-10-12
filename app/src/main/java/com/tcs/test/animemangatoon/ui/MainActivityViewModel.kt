package com.tcs.test.animemangatoon.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tcs.test.animemangatoon.data.Repository
import com.tcs.test.animemangatoon.data.model.Article
import com.tcs.test.animemangatoon.data.prefs.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    fun storeArticles(articles: List<Article>){
        viewModelScope.launch {
            for (a in articles) {
                repository.insertArticle(a)
            }
        }
    }
}