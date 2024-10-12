package com.tcs.test.animemangatoon.ui.home

import com.tcs.test.animemangatoon.data.model.Article

interface OnArticleItemClickListener {
    fun onImageButtonClicked(article: Article,position:Int)
    fun onAnswerClicked(article: Article)
}