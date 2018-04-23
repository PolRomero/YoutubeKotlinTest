package com.example.domain.model

import java.util.*

data class History (val history : ArrayList<Video> = ArrayList<Video>()) {
    fun add(video : Video) = history.add(video)
}