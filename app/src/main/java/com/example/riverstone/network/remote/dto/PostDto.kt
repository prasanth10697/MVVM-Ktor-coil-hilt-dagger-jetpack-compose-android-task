package com.example.riverstone.network.remote.dto

import kotlinx.serialization.Serializable
import com.example.riverstone.model.PostModel

@Serializable
data class PostDto(
    val body: String,
    val title: String,
    val id: Int,
    val userId: Int
)

fun PostDto.toPost(): PostModel {
    return PostModel(
        body = body,
        title = title,
        userId = userId
    )
}