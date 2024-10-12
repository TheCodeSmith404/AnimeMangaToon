package com.tcs.test.animemangatoon.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_table")
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var name:String,
    var img:String,
    var imgSmall:String,
    var description:String,
    var writer:String,
    var art:String,
    var reads:String,
    var isFav:Boolean,
    var isReviewed:Boolean,
    var rating:Int)