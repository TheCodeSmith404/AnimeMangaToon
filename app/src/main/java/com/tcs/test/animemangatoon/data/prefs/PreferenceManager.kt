package com.tcs.test.animemangatoon.data.prefs

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    // Save and get permission granted
    var firstStart: Boolean
        get() = sharedPreferences.getBoolean("first_start", true)
        set(value) {
            sharedPreferences.edit().putBoolean("first_start", value).apply()
        }
}