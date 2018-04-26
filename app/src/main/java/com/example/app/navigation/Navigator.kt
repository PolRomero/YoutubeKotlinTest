package com.example.app.navigation

import android.content.Intent
import com.example.app.view.fragment.HistoryFragment
import com.example.app.view.activity.RootActivity
import com.example.app.view.fragment.SearchFragment

fun goToHistory(context: RootActivity<*>) {
    context.startActivity(Intent(context, HistoryFragment::class.java))
    context.finish()
}

fun goToSearch(context: RootActivity<*>) {
    context.startActivity(Intent(context, SearchFragment::class.java))
    context.finish()
}

