package com.private_projects.pikabu_reader.data

import com.private_projects.pikabu_reader.domain.ElementsReceiver
import com.private_projects.pikabu_reader.domain.LinkReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.jsoup.Connection
import org.jsoup.Connection.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException

class ElementsReceiverImpl : ElementsReceiver {
    private val linkReceiver = LinkReceiver()
    private var url = ""
    private var doc: Document? = null
    private var elements: Elements? = null
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var resp: Response? = null
    private val resultList = mutableListOf<Element>()

    override fun get(chapter: String, page: Int) {
        url = linkReceiver.get(chapter, page)

        try {
            val connection: Connection = Jsoup.connect(url)
            connection.userAgent("Chrome/107.0.5304.88 Safari/537.36")
            connection.referrer("http://www.google.com")
            connection.method(Connection.Method.GET)
            doc = connection.url(url).get()
            doc.let { document ->
                elements =
                    document?.select("article.story_exp-unit-redesign") // Фильтр таргет рекламы
                resp = connection.execute()
                elements?.forEach { element ->
                    if (element.select("a")
                            .attr("data-name") != "specials"
                    ) { // Фильтр рекламных постов
                        resultList.add(element)
                    }
                }
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun response(): Response? {
        return resp
    }

    private fun writeResult() {

    }
}