package com.example.data.datasource.network

import com.example.data.model.HistoryData
import io.reactivex.Single

interface NetworkDatasource {
    fun downloadResults() : Single<HistoryData>
}