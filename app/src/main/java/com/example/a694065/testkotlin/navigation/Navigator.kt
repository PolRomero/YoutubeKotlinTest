package com.example.a694065.testkotlin.navigation

import android.content.Intent
import com.example.a694065.testkotlin.view.activity.HistoryActivity
import com.example.a694065.testkotlin.view.activity.RootActivity
import com.example.a694065.testkotlin.view.activity.SearchActivity

fun goToHistory(context: RootActivity<*>) {
    context.startActivity(Intent(context, HistoryActivity::class.java))
    context.finish()
}

fun goToSearch(context: RootActivity<*>) {
    context.startActivity(Intent(context, SearchActivity::class.java))
    context.finish()
}

