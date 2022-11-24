package com.private_projects.pikabu_reader.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.private_projects.pikabu_reader.databinding.ActivityMainBinding
import com.private_projects.pikabu_reader.ui.hot.HotFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(binding.mainLayout.id, HotFragment.newInstance())
            .commit()
    }
}