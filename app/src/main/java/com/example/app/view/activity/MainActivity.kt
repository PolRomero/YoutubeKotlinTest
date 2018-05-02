package com.example.app.view.activity

import android.content.res.Configuration
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ListView
import com.example.a694065.testkotlin.R
import com.example.app.constants.Constants.LAYOUT_EQUAL
import com.example.app.constants.Constants.PLAYER_KEY
import com.example.app.navigation.goToSearch
import com.example.app.presenter.MainPresenter
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import kotlinx.android.synthetic.main.split_layout_vertical.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class MainActivity: RootActivity<MainPresenter.View>(), MainPresenter.View {

    override var resourceId = R.layout.split_layout_vertical
    override val activityModule: Kodein.Module = Kodein.Module {
        bind() from provider {
            MainPresenter(view = this@MainActivity)
        }
    }
    override val presenter: MainPresenter by instance()
    lateinit var player: YouTubePlayer
    lateinit var mdrawer: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindLayout()
        Log.d("Activity", "Activity created")
        if(savedInstanceState == null) {
            goToSearch(fragmentManager, R.id.second_fragment)
        }

    }

    override fun onResume() {
        super.onResume()
        configLayoutOrientation()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        configLayoutOrientation()
    }

    private fun bindLayout() {

        val drawer = findViewById<DrawerLayout>(R.id.drawer)
        mdrawer = ActionBarDrawerToggle(this, drawer, R.string.app_name, R.string.app_name)
        drawer.addDrawerListener(mdrawer)
        mdrawer.syncState()
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val drawerContent = findViewById<ListView>(R.id.left_drawer)
        drawerContent.setOnItemClickListener { parent, view, position, id ->
            presenter.navigateTo(position)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(mdrawer.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun configLayoutOrientation() {
        val firstFragment = fragmentManager.findFragmentById(R.id.first_fragment)
        if(firstFragment == null) {
            second_fragment.layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT
            second_fragment.layoutParams.height = FrameLayout.LayoutParams.MATCH_PARENT
        }
        if(resources.configuration.orientation == ORIENTATION_LANDSCAPE) {
            linear_container.orientation = LinearLayout.HORIZONTAL
            if(firstFragment != null) {
                first_fragment.layoutParams.width = LAYOUT_EQUAL
                first_fragment.layoutParams.height = FrameLayout.LayoutParams.MATCH_PARENT
                second_fragment.layoutParams.width = LAYOUT_EQUAL
                second_fragment.layoutParams.height = FrameLayout.LayoutParams.MATCH_PARENT
            }
        } else {
            linear_container.orientation = LinearLayout.VERTICAL
            if(firstFragment != null) {
                first_fragment.layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT
                first_fragment.layoutParams.height = LAYOUT_EQUAL
                second_fragment.layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT
                second_fragment.layoutParams.height = LAYOUT_EQUAL
            }
        }
    }


    override fun loadVideo(videoId: String) {
        Log.d("Id", videoId)

        var firstFragment = fragmentManager.findFragmentById(R.id.first_fragment)
        if(firstFragment == null) {
            val youtubeFragment = YouTubePlayerFragment()
            fragmentManager.beginTransaction()
                    .replace(R.id.first_fragment, youtubeFragment)
                    .commitAllowingStateLoss()
            youtubeFragment.initialize(PLAYER_KEY, object: YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
                    if(p1 != null) player = p1
                    player.cueVideo(videoId)
                    configLayoutOrientation()
                }

                override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {

                }
            })
        } else {
            player.cueVideo(videoId)
            configLayoutOrientation()
        }
    }


}