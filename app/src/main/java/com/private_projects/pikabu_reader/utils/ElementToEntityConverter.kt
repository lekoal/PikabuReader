package com.private_projects.pikabu_reader.utils

import androidx.core.text.parseAsHtml
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.private_projects.pikabu_reader.data.entities.*
import org.jsoup.nodes.Element

class ElementToEntityConverter {
    private val entityList = mutableListOf<CommonPostEntity>()
    private val _resultEntity = MutableLiveData<List<CommonPostEntity>>()
    val resultEntity: LiveData<List<CommonPostEntity>> = _resultEntity

    fun insertRawData(elements: List<Element>) {
        elements.forEach { element ->
            val textList = mutableListOf<TextBlockEntity>()
            val imageList = mutableListOf<ImageBlockEntity>()
            val videoList = mutableListOf<VideoBlockEntity>()
            val postId = element.select("article").attr("data-story-id")
            val title = element.select("h2.story__title").text()
            val user = element.select("a.story__user-link user__nick").attr("data-name")
            val rating = element.select("div.story__rating-count").text()
            val time = element.select("time").text()
            val views = element.select("span.story__views-count").text()
            val partialPost = PostEntity(
                postId, title, user, rating, time, views
            )

            element.select("div.story-block").forEachIndexed { bIndex, block ->
                if (block.attr("class") == "story-block story-block_type_text") {
                    block.select("p").forEach { text ->
                        val htmlText = "${ text.html() }\n"
                        val formattedText = htmlText.parseAsHtml()
                        textList.add(
                            TextBlockEntity(
                                bIndex,
                                formattedText.toString()
                            )
                        )
                    }
                }

                if (block.attr("class") == "story-block story-block_type_image") {
                    imageList.add(
                        ImageBlockEntity(
                            bIndex,
                            block.select("a.image-link").select("img")
                                .attr("src")
                        )
                    )
                }

                if (block.attr("class") == "story-block story-block_type_video") {
                    videoList.add(
                        VideoBlockEntity(
                            bIndex,
                            block.select("div.player").attr("data-source")
                                    + ".mp4"
                        )
                    )
                }
                entityList.add(
                    CommonPostEntity(
                        partialPost,
                        textList,
                        imageList,
                        videoList
                    )
                )
            }
        }
        _resultEntity.postValue(entityList)
    }

}