package com.tcs.test.animemangatoon.data

import androidx.lifecycle.LiveData
import com.tcs.test.animemangatoon.data.database.ArticleDao
import com.tcs.test.animemangatoon.data.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(
    private val articleDao: ArticleDao
)  {

    suspend fun insertArticle(article: Article) {
        withContext(Dispatchers.IO) {
            articleDao.insertArticle(article)
        }
    }

    suspend fun updateArticle(article: Article) {
        withContext(Dispatchers.IO) {
            articleDao.updateArticle(article)
        }
    }

    suspend fun deleteArticle(article: Article) {
        withContext(Dispatchers.IO) {
            articleDao.deleteArticle(article)
        }
    }

    suspend fun getArticleById(id: Int): Article {
        return withContext(Dispatchers.IO) {
            articleDao.getArticleById(id)
        }
    }

    fun getAllArticles(): LiveData<MutableList<Article>> {
        return articleDao.getAllArticles()
    }
    fun getAllFavArticles(): LiveData<MutableList<Article>>{
        return articleDao.getAllFavArticles()
    }

    suspend fun deleteAllArticles() {
        withContext(Dispatchers.IO) {
            articleDao.deleteAllArticles()
        }
    }
}