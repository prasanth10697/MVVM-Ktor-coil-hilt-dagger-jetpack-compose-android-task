package com.example.riverstone.presentation

import com.example.riverstone.model.PostModel

data class PostsState(
    val posts: List<PostModel>? = null,
    val loading: Boolean = false,
    val error: String? = null
)
