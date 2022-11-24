package com.private_projects.pikabu_reader.domain

import kotlinx.coroutines.flow.Flow
import org.jsoup.Connection.Response
import org.jsoup.nodes.Element

const val HOT = "hot"
const val BEST = "best"
const val FRESH = "fresh"

interface ElementsReceiver {
    val elementList: List<Element>
    fun get(chapter: String, page: Int)
    fun getData(): Flow<List<Element>>
    fun response() : Response?
}