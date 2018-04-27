package com.example.app.presenter

import com.example.app.navigation.NavigationManager
import io.reactivex.disposables.Disposable

class MainPresenter(view: View): Presenter<MainPresenter.View>(view) {

    private lateinit var videoDisposable: Disposable

    override fun initialize() {
    }

    override fun resume() {
        super.resume()
        videoDisposable = navigationManager.videoSubject.subscribe {
            view.loadVideo(it)
        }
    }

    override fun destroy() {

    }

    override fun stop() {
        super.stop()
        videoDisposable.dispose()
    }

    fun navigateTo(position: Int) {
        when(position) {
            NavigationManager.ScreenEvent.VIDEO_SEARCH -> view.navigateToSearch()
            NavigationManager.ScreenEvent.HISTORY -> view.navigateToHistory()
        }
    }

    interface View: Presenter.View {
        fun navigateToHistory()
        fun navigateToSearch()
        fun loadVideo(videoId: String)
    }
}