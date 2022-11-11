package com.private_projects.pikabu_reader.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.private_projects.pikabu_reader.R
import com.private_projects.pikabu_reader.data.ElementsReceiverImpl
import com.private_projects.pikabu_reader.databinding.ActivityMainBinding
import com.private_projects.pikabu_reader.domain.BEST
import com.private_projects.pikabu_reader.domain.ElementsReceiver
import com.private_projects.pikabu_reader.utils.ElementToEntityConverter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val elementsReceiver: ElementsReceiver by lazy {
        ElementsReceiverImpl()
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
            val textView = TextView(this)
            textView.text = it[1].texts?.get(0)?.text
            findViewById<ConstraintLayout>(R.id.main_layout).addView(textView)
            println(it[1].images)
        }

    }
}