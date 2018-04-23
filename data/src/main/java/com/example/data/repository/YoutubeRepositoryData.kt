package com.example.data.repository

import com.example.data.api.ApiCaller
import com.example.data.mappers.toModel
import com.example.domain.model.History
import com.example.domain.repository.YoutubeRepository
import io.reactivex.Single
import java.util.*

class YoutubeRepositoryData: YoutubeRepository  {

    override fun getVideoSearch(search: String): Single<History> =
            ApiCaller.getResults(search)
                    .map{ it.toModel() }

}