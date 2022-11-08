package com.private_projects.pikabu_reader.data.entities

data class PikabuPostEntity(
    val id: Long,
    val title: String,
    val user: String,
    val rating: Int,
    val time: String,
    val views: String
)
