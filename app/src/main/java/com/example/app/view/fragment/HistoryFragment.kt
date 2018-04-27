package com.example.app.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.a694065.testkotlin.R
import com.example.app.constants.Constants
import com.example.app.presenter.HistoryPresenter
import com.example.app.view.adapter.VideoListAdapter
import com.squareup.picasso.Picasso
import com.squareup.picasso.clear
import kotlinx.android.synthetic.main.search_layout.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class HistoryFragment : RootFragment<HistoryPresenter.View>(), HistoryPresenter.View {


    override val resourceId = R.layout.search_layout
    lateinit var adapter: VideoListAdapter

    companion object {
        fun newInstance() = HistoryFragment()
    }

    override val fragmentModule: Kodein.Module = Kodein.Module {
        bind() from provider {
            HistoryPresenter(getHistoryViewUseCase = instance(),
                    updateHistoryWithHistoryUseCase = instance(),
                    getHistoryByTitleUseCase = instance(),
                    view = this@HistoryFragment)
        }
    }

    override val presenter: HistoryPresenter by instance()


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun startAdapter() {
        adapter = VideoListAdapter(onItemClick = presenter.onItemClick())
        recycler_list.adapter = adapter
        recycler_list.layoutManager = LinearLayoutManager(activity)
        presenter.getAllHistory()
    }

    private fun restartAdapter(savedInstanceState: Bundle) {
        adapter = savedInstanceState.getParcelable(Constants.ADAPTER_KEY)
        adapter.modifyItemClick(presenter.onItemClick())
        recycler_list.adapter = adapter
        recycler_list.layoutManager = LinearLayoutManager(activity)
    }

    override fun updateHistory() {
        recycler_list.scrollToPosition(0)
        adapter.updateItems(presenter.history)
    }

    override fun clearMemory() {
        Picasso.get().clear()
    }

    private fun  registerEditListener() {
        search_view.setOnQueryTextListener(object: android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.searchResults(search_view.query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText?.length == 0) {
                    presenter.getAllHistory()
                    return true
                }
                return false
            }

        })
    }
}