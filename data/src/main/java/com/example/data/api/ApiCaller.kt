package com.example.data.api
import com.example.data.model.HistoryData
import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


object ApiCaller {

    private val youtubeApi: YoutubeApi

    init {
        val gson = GsonBuilder().registerTypeAdapter(HistoryData::class.java, SearchAdapter()).create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        youtubeApi = retrofit.create(YoutubeApi::class.java)
    }

    fun getResults(query: String): Single<HistoryData> =
            youtubeApi.searchResults("AIzaSyCNn7xuWI5wjCgZQ39ghhyxfPQQV2uBx_s",
                    "snippet", query)

}