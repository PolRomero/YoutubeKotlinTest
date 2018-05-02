package com.example.app.navigation

import android.app.FragmentManager
import com.example.app.view.fragment.HistoryFragment
import com.example.app.view.fragment.SearchFragment

fun goToHistory(fragmentManager: FragmentManager, fragmentContainer: Int) {
    val fragment = HistoryFragment.newInstance()

    fragmentManager.beginTransaction()
            .replace(fragmentContainer, fragment)
            .commit()
}

fun goToSearch(fragmentManager: FragmentManager, fragmentContainer: Int) {
    val fragment = SearchFragment.newInstance()

    fragmentManager.beginTransaction()
            .replace(fragmentContainer, fragment)
            .commit()
}

