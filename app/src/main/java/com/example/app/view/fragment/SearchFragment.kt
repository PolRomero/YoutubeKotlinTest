package com.example.app.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.SearchView
import com.example.a694065.testkotlin.R
import com.example.app.constants.Constants
import com.example.app.model.HistoryView
import com.example.app.presenter.SearchPresenter
import com.example.app.view.adapter.VideoListAdapter
import com.squareup.picasso.Picasso
import com.squareup.picasso.clear
import kotlinx.android.synthetic.main.search_layout.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class SearchFragment : RootFragment<SearchPresenter.View>(), SearchPresenter.View {


    override val resourceId = R.layout.search_layout


    override val fragmentModule: Kodein.Module = Kodein.Module {
        bind() from provider {
            SearchPresenter(searchInYoutubeUseCase = instance(),
                    updateHistoryUseCase = instance(),
                    view = this@SearchFragment)
        }
    }

    override val presenter: SearchPresenter by instance()
    private lateinit var adapter: VideoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerEditListener()

        if(savedInstanceState != null) {
            restartAdapter(savedInstanceState)
        } else {
            startAdapter()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelable(Constants.ADAPTER_KEY, adapter)
        super.onSaveInstanceState(outState)
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
        recycler_list.layoutManager = LinearLayoutManager(activity)
    }

    private fun restartAdapter(savedInstanceState: Bundle) {
        adapter = savedInstanceState.getParcelable(Constants.ADAPTER_KEY)
        adapter.modifyItemClick(presenter.onItemClick())
        recycler_list.adapter = adapter
        recycler_list.layoutManager = LinearLayoutManager(activity)
    }

}

