package com.private_projects.pikabu_reader.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.private_projects.pikabu_reader.data.ElementsReceiverImpl
import com.private_projects.pikabu_reader.databinding.ActivityMainBinding
import com.private_projects.pikabu_reader.domain.BEST
import com.private_projects.pikabu_reader.domain.CommonDatabaseHelper
import com.private_projects.pikabu_reader.domain.ElementsReceiver
import com.private_projects.pikabu_reader.utils.ElementToEntityConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val elementsReceiver: ElementsReceiver by lazy {
        ElementsReceiverImpl()
    }
    private val databaseHelper: CommonDatabaseHelper by lazy {
        get(named("common_database_helper"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        elementsReceiver.get(BEST, 1)

        val converter = ElementToEntityConverter()

        elementsReceiver.elementList.observe(this) {
            converter.insertRawData(it)
        }

        converter.resultEntity.observe(this) {

            CoroutineScope(Dispatchers.IO).launch {
                it.forEach { commonPostEntity ->
                    databaseHelper.insertPartialPost(commonPostEntity.postEntity)
                    commonPostEntity.texts?.forEach { text ->
                        databaseHelper.insertText(text)
                    }
                    commonPostEntity.images?.forEach { image ->
                        databaseHelper.insertImage(image)
                    }
                    commonPostEntity.videos?.forEach { video ->
                        databaseHelper.insertVideo(video)
                    }
                }
            }
        }
    }
}