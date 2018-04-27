package com.example.app.presenter

import com.example.app.navigation.NavigationManager
import io.reactivex.disposables.Disposable


abstract class Presenter<out V: Presenter.View> (val view: V){

    protected val navigationManager = NavigationManager
    var currentScreen = NavigationManager.ScreenEvent.VIDEO_SEARCH
    private lateinit var eventDisposable: Disposable

    abstract fun initialize()

    open fun resume() {
        eventDisposable = navigationManager.eventSubject.subscribe{
            currentScreen = it
        }
    }

    open fun stop() {
        eventDisposable.dispose()
    }

    abstract fun destroy()


    interface View {

    }
}



