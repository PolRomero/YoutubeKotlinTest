package com.example.app.view.activity


import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout
import android.widget.ListView
import com.example.a694065.testkotlin.R
import com.example.app.navigation.NavigationManager
import com.example.app.navigation.goToHistory
import com.example.app.navigation.goToSearch
import com.example.app.presenter.MainPresenter
import com.example.app.presenter.Presenter
import com.example.app.view.App
import com.example.app.view.fragment.HistoryFragment
import com.example.app.view.fragment.SearchFragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware



abstract class RootActivity<out V: Presenter.View> : AppCompatActivity(), KodeinAware, Presenter.View {

    abstract val presenter: Presenter<Presenter.View>
    abstract var resourceId: Int

    abstract val activityModule: Kodein.Module

    override val kodein = Kodein.lazy {
        extend((application as App).kodein)
        import(activityModule)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.initialize()
        setContentView(resourceId)
    }

    override fun onResume() {
        super.onResume()
        presenter.resume()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    fun navigateToHistory() {
        goToHistory(fragmentManager, R.id.second_fragment)
    }

    fun navigateToSearch() {
        goToSearch(fragmentManager, R.id.second_fragment)
    }

}