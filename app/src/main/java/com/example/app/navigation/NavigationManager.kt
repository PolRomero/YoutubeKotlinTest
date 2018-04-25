package com.example.app.navigation

import io.reactivex.subjects.PublishSubject

object NavigationManager {

    val eventSubject: PublishSubject<ScreenEvent> = PublishSubject.create()

    enum class ScreenEvent {
        VIDEO_SEARCH,
        HISTORY
    }
}