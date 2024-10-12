package com.tcs.test.animemangatoon.ui.reviews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel


class ReviewsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "User Reviews are here!"
    }
    val text: LiveData<String> = _text
}