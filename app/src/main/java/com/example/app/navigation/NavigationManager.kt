package com.example.app.navigation

import io.reactivex.subjects.PublishSubject

object NavigationManager {

    val eventSubject: PublishSubject<Int> = PublishSubject.create()
    val videoSubject: PublishSubject<String> = PublishSubject.create()

    object ScreenEvent {
        val VIDEO_SEARCH = 0
        val HISTORY = 1
    }
}