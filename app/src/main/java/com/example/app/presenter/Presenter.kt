package com.example.app.presenter

import com.example.app.navigation.NavigationManager
import io.reactivex.disposables.Disposable


abstract class Presenter<out V: Presenter.View> (val view: V){

    protected val navigationManager = NavigationManager
    private lateinit var currentScreen: NavigationManager.ScreenEvent
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

    fun navigateTo(screenEvent: NavigationManager.ScreenEvent) {
        if(screenEvent != currentScreen) {
            when(screenEvent) {
                NavigationManager.ScreenEvent.VIDEO_SEARCH -> view.navigateToSearch()
                NavigationManager.ScreenEvent.HISTORY -> view.navigateToHistory()
            }
        }
    }

    open fun onTrimMemory() =
            view.clearMemory()

    interface View {
        fun clearMemory()

        fun navigateToSearch()
        fun navigateToHistory()
    }
}



