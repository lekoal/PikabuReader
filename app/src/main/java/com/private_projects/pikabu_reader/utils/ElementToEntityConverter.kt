package com.private_projects.pikabu_reader.utils

import androidx.core.text.parseAsHtml
import com.private_projects.pikabu_reader.data.entities.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.nodes.Element

class ElementToEntityConverter {

    fun insertRawData(elements: List<Element>): Flow<List<CommonPostEntity>> = flow {
        val entityList = mutableListOf<CommonPostEntity>()
        elements.forEach { element ->
            if (element.select("a.user__nick").isNotEmpty() &&
                element.select("time").isNotEmpty()
            ) {
                val textList = mutableListOf<TextBlockEntity>()
                val imageList = mutableListOf<ImageBlockEntity>()
                val videoList = mutableListOf<VideoBlockEntity>()
                val postId = element.select("article.story")
                    .attr("data-story-id")
                val title = element.select("h2.story__title").text()
                val user = element.select("a.user__nick").text()
                val rating = element.select("div.story__rating-count").text()
                val time = element.select("time").text()
                val partialPost = PostEntity(
                    postId, title, user, rating, time
                )
                element.select("div.story-block").forEachIndexed { bIndex, block ->
                    when (block.attr("class")) {
                        "story-block story-block_type_text" -> {
                            block.select("p").forEach { text ->
                                val htmlText = "${text.html()}\n"
                                val formattedText = htmlText.parseAsHtml()
                                textList.add(
                                    TextBlockEntity(
                                        ownerId = element.select("article.story")
                                            .attr("data-story-id"),
                                        position = bIndex,
                                        text = formattedText.toString()
                                    )
                                )
                            }
                        }
                        "story-block story-block_type_image" -> {
                            block.select("a.image-link").let {
                                imageList.add(
                                    ImageBlockEntity(
                                        ownerId = element.select("article.story")
                                            .attr("data-story-id"),
                                        position = bIndex,
                                        url = it.select("img")
                                            .attr("data-large-image")
                                    )
                                )
                            }
                        }
                        "story-block story-block_type_video" -> {
                            videoList.add(
                                VideoBlockEntity(
                                    ownerId = element.select("article.story")
                                        .attr("data-story-id"),
                                    position = bIndex,
                                    url = block.select("div.player")
                                        .attr("data-source") + ".mp4"
                                )
                            )
                        }
                    }
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
        emit(entityList)
    }
}