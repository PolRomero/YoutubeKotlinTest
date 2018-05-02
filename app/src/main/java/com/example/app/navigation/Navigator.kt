package com.example.app.navigation

import android.app.FragmentManager
import com.example.app.presenter.Presenter
import com.example.app.view.activity.MainActivity
import com.example.app.view.fragment.HistoryFragment
import com.example.app.view.fragment.RootFragment
import com.example.app.view.fragment.SearchFragment

fun goToHistory(fragmentManager: FragmentManager, fragmentContainer: Int) {
    val fragment = HistoryFragment.newInstance()

    fragmentManager.beginTransaction()
            .replace(fragmentContainer, fragment)
            .addToBackStack(HistoryFragment.className())
            .commit()
}

fun goToSearch(fragmentManager: FragmentManager, fragmentContainer: Int) {
    val fragment = SearchFragment.newInstance()

    fragmentManager.beginTransaction()
            .replace(fragmentContainer, fragment)
            .addToBackStack(SearchFragment.className())
            .commit()
}



