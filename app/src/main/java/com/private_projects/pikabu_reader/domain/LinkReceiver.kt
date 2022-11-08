package com.private_projects.pikabu_reader.domain

import com.private_projects.pikabu_reader.data.PikabuUrlData

class LinkReceiver {
    fun get(chapter: String, page: Int): String {
        var url = ""
        when (chapter) {
            HOT -> {
                url = "${PikabuUrlData.hot}$page"
            }
            BEST -> {
                url = "${PikabuUrlData.best}$page"
            }
            FRESH -> {
                url = "${PikabuUrlData.fresh}$page"
            }
        }
        return url
    }
}