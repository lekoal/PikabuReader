package com.private_projects.pikabu_reader.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.private_projects.pikabu_reader.data.ElementsReceiverImpl
import com.private_projects.pikabu_reader.databinding.ActivityMainBinding
import com.private_projects.pikabu_reader.domain.ElementsReceiver
import com.private_projects.pikabu_reader.domain.HOT
import com.private_projects.pikabu_reader.utils.ElementToEntityConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val elementsReceiver: ElementsReceiver by lazy {
        ElementsReceiverImpl()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        elementsReceiver.get(HOT, 1)

        val converter = ElementToEntityConverter()

        elementsReceiver.elementList.observe(this) {
            converter.insertRawData(it)
        }

        converter.resultEntity.observe(this) {
            println(it[0])
        }

    }
}