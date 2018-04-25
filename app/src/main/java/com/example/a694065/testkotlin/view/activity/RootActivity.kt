package com.example.a694065.testkotlin.view.activity

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.a694065.testkotlin.R
import com.example.a694065.testkotlin.navigation.goToHistory
import com.example.a694065.testkotlin.navigation.goToSearch
import com.example.a694065.testkotlin.presenter.Presenter
import com.example.a694065.testkotlin.view.App
import kotlinx.android.synthetic.main.search_layout.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein


abstract class RootActivity<out V: Presenter.View> : AppCompatActivity(), KodeinAware, Presenter.View {

    abstract val presenter: Presenter<V>
    abstract val resourceId: Int

    abstract val activityModule: Kodein.Module

    override val kodein = Kodein.lazy {
        extend((application as App).kodein)
        import(activityModule)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.initialize()
        setContentView(resourceId)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mdrawer = object : ActionBarDrawerToggle(this, drawer, R.string.app_name, R.string.app_name) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }
        }

        drawer.addDrawerListener(mdrawer)
        mdrawer.syncState()

        left_drawer.setOnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> goToSearch(this)
                1 -> goToHistory(this)
            }
        }
    }


    override fun onResume() {
        super.onResume()
        presenter.resume()
    }

    override fun onStop() {
        super.onStop()
        //presenter.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        presenter.onTrimMemory(level)
    }
}