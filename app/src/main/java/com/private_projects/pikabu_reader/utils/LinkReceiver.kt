package com.private_projects.pikabu_reader.utils

import com.private_projects.pikabu_reader.data.PikabuUrlData
import com.private_projects.pikabu_reader.domain.BEST
import com.private_projects.pikabu_reader.domain.FRESH
import com.private_projects.pikabu_reader.domain.HOT

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