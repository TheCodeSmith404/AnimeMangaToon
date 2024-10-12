package com.tcs.test.animemangatoon.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.tcs.test.animemangatoon.data.Repository
import com.tcs.test.animemangatoon.data.database.ArticleDao
import com.tcs.test.animemangatoon.data.database.ArticleDatabase
import com.tcs.test.animemangatoon.data.prefs.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // SingletonComponent ensures single instance per app
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): ArticleDatabase {
        return Room.databaseBuilder(
            app,
            ArticleDatabase::class.java,
            "article_database_v1"
        ).build()
    }

    @Provides
    fun provideArticleDao(db: ArticleDatabase): ArticleDao {
        return db.articleDao()
    }

    @Provides
    @Singleton
    fun provideArticleRepository(articleDao: ArticleDao): Repository {
        return Repository(articleDao)
    }

    @Provides
    @Singleton
    fun providePreferenceManager(app: Application): PreferenceManager {
        return PreferenceManager(app.applicationContext)
    }
}