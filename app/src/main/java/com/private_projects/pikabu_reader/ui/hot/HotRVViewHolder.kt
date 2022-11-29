package com.private_projects.pikabu_reader.ui.hot

import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.private_projects.pikabu_reader.R
import com.private_projects.pikabu_reader.data.entities.CommonPostEntity
import kotlin.math.max

class HotRVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val layout: LinearLayout = itemView.findViewById(R.id.rv_hot_item_layout)
    private val title: AppCompatTextView = itemView.findViewById(R.id.hot_item_title)
    private val author: AppCompatTextView = itemView.findViewById(R.id.hot_item_author)

    fun bind(item: CommonPostEntity) {
        val count = max(max(item.texts?.size!!, item.images?.size!!), item.videos?.size!!)
        title.text = item.postEntity.title
        author.text = item.postEntity.user

        for (i in 0 until count) {
            item.texts.forEach { textBlock ->
                if (textBlock.position == i) {
                    val text = AppCompatTextView(itemView.context)
                    text.textSize = 20f
                    text.text = textBlock.text
                    layout.addView(text)
                }
            }
            item.images.forEach { imageBlock ->
                if (imageBlock.position == i) {
                    val image = AppCompatTextView(itemView.context)
                    image.textSize = 20f
                    image.text = imageBlock.url
                    layout.addView(image)
                }
            }
            item.videos.forEach { videoBlock ->
                if (videoBlock.position == i) {
                    val video = AppCompatTextView(itemView.context)
                    video.textSize = 20f
                    video.text = videoBlock.url
                    layout.addView(video)
                }
            }
        }
    }
}