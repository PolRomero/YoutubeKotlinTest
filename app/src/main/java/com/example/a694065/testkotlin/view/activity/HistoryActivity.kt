package com.example.a694065.testkotlin.view.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.a694065.testkotlin.R
import com.example.a694065.testkotlin.presenter.HistoryPresenter
import com.example.a694065.testkotlin.view.adapter.VideoListAdapter
import com.squareup.picasso.Picasso
import com.squareup.picasso.clear
import kotlinx.android.synthetic.main.search_layout.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class HistoryActivity : RootActivity<HistoryPresenter.View>(), HistoryPresenter.View {


    override val resourceId = R.layout.search_layout
    lateinit var adapter: VideoListAdapter

    override val activityModule: Kodein.Module = Kodein.Module {
        bind() from provider {
            HistoryPresenter(getHistoryView = instance(),
                    updateHistoryWithHistory = instance(),
                    view = this@HistoryActivity)
        }
    }
    override val presenter: HistoryPresenter by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //registerEditListener()
        recycler_list.layoutManager = LinearLayoutManager(this)
    }

    override fun startHistory() {
        adapter = VideoListAdapter(presenter.history, {})
        recycler_list.adapter = adapter
    }

    override fun updateHistory() {
        adapter.updateItems(presenter.history)
    }




   /* override fun updateSearchResults(history: HistoryView) {
        adapter.updateItems(history)
    }

    private fun registerEditListener() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.searchResults(search_view.query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }*/
}