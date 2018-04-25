package com.example.app.navigation

import android.content.Intent
import com.example.app.view.activity.HistoryActivity
import com.example.app.view.activity.RootActivity
import com.example.app.view.activity.SearchActivity

fun goToHistory(context: RootActivity<*>) {
    context.startActivity(Intent(context, HistoryActivity::class.java))
    context.finish()
}

fun goToSearch(context: RootActivity<*>) {
    context.startActivity(Intent(context, SearchActivity::class.java))
    context.finish()
}

