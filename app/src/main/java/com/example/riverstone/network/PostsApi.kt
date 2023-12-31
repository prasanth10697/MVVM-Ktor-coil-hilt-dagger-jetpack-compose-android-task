package com.example.riverstone.network

import com.example.riverstone.network.remote.dto.PostDto

interface PostsApi {

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com"
        const val POSTS_URL = "$BASE_URL/posts"
    }

    suspend fun getPosts(): List<PostDto>

    suspend fun postPost(post: PostDto): PostDto?
}