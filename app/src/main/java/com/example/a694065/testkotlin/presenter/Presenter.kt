package com.example.a694065.testkotlin.presenter

import com.example.a694065.testkotlin.navigation.NavigationManager
import io.realm.Realm

abstract class Presenter<out V: Presenter.View> (val view: V){

    protected val navigationManager = NavigationManager

    abstract fun initialize()

    abstract fun resume()

    abstract fun stop()

    abstract fun destroy()

    abstract fun onTrimMemory(level: Int)

    interface View
}



