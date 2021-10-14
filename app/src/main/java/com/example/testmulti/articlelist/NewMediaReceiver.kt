package com.example.testmulti.articlelist

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timber.log.Timber

class NewMediaReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Timber.d("Bhaijan achchayee working")
    }
}