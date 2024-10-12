package com.tcs.test.animemangatoon.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tcs.test.animemangatoon.data.model.Article

@Dao
interface ArticleDao {

    @Insert
    suspend fun insertArticle(article: Article)

    @Update
    suspend fun updateArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * FROM article_table WHERE id = :id")
    suspend fun getArticleById(id: Int): Article?

    @Query("SELECT * FROM article_table")
    suspend fun getAllArticles(): List<Article>

    @Query("SELECT * FROM article_table WHERE isFav=1")
    fun getAllFavArticles():LiveData<MutableList<Article>>

    @Query("DELETE FROM article_table")
    suspend fun deleteAllArticles()
}
