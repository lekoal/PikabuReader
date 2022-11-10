package com.private_projects.pikabu_reader.domain

import androidx.lifecycle.LiveData
import org.jsoup.Connection.Response
import org.jsoup.nodes.Element

const val HOT = "hot"
const val BEST = "best"
const val FRESH = "fresh"

interface ElementsReceiver {
    val elementList: LiveData<List<Element>>
    fun get(chapter: String, page: Int)
    fun response() : Response?
}