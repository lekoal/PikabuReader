package com.private_projects.pikabu_reader.data

import com.private_projects.pikabu_reader.domain.ElementsReceiver
import com.private_projects.pikabu_reader.utils.LinkReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.jsoup.Connection
import org.jsoup.Connection.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException

class ElementsReceiverImpl : ElementsReceiver {
    private val linkReceiver = LinkReceiver()
    private var doc: Document? = null
    private var elements: Elements? = null
    private var resp: Response? = null
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    override val elementList = mutableListOf<Element>()

    override fun get(chapter: String, page: Int) {
        val url = linkReceiver.get(chapter, page)
        val tempList = mutableListOf<Element>()

        coroutineScope.launch {
            try {
                val connection: Connection = Jsoup.connect(url)
                connection.userAgent("Chrome/107.0.5304.88 Safari/537.36")
                connection.referrer("http://www.google.com")
                connection.method(Connection.Method.GET)
                resp = connection.execute()
                doc = connection.url(url).get()
                doc.let { document ->

                    elements =
                        document?.select("article.story")
                    elements?.forEach { element ->
                        tempList.add(element)
                    }
                }
                elementList.addAll(tempList)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun getData(): Flow<List<Element>> = flow {
        emit(elementList)
    }

    override fun response(): Response? {
        return resp
    }
}