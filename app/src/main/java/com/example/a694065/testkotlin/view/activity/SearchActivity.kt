package com.example.a694065.testkotlin.view.activity

import android.content.ComponentCallbacks2
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.SearchView
import com.example.a694065.testkotlin.R
import com.example.a694065.testkotlin.model.HistoryView
import com.example.a694065.testkotlin.presenter.SearchPresenter
import com.example.a694065.testkotlin.view.adapter.VideoListAdapter
import com.squareup.picasso.Picasso
import com.squareup.picasso.clear
import kotlinx.android.synthetic.main.search_layout.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class SearchActivity : RootActivity<SearchPresenter.View>(), SearchPresenter.View {


    override val resourceId = R.layout.search_layout


    override val activityModule: Kodein.Module = Kodein.Module {
        bind() from provider {
            SearchPresenter(searchInYoutubeUseCase = instance(),
                    updateHistoryUseCase = instance(),
                    view = this@SearchActivity)}
    }

    override val presenter: SearchPresenter by instance()
    private lateinit var adapter: VideoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startAdapter()
        registerEditListener()
    }
    override fun updateSearchResults(history: HistoryView) {
        recycler_list.scrollToPosition(0)
        adapter.updateItems(history)
    }

    override fun clearMemory() {
        Picasso.get().clear()
    }

    private fun  registerEditListener() {
        search_view.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.searchResults(search_view.query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun startAdapter() {
        adapter = VideoListAdapter(onItemClick = presenter.onItemClick())
        recycler_list.adapter = adapter
        recycler_list.layoutManager = LinearLayoutManager(this)
    }

}

