package com.private_projects.pikabu_reader.domain

import org.jsoup.Connection.Response

const val HOT = "hot"
const val BEST = "best"
const val FRESH = "fresh"

interface ElementsReceiver {
    fun get(chapter: String, page: Int)
    fun response() : Response?
}