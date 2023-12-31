package com.example.riverstone.network

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import com.example.riverstone.network.remote.dto.PostDto

class PostsApiImpl(private val client: HttpClient) : PostsApi {

    override suspend fun getPosts(): List<PostDto> {
        return client.get { url(PostsApi.POSTS_URL) }
    }

    override suspend fun postPost(post: PostDto): PostDto? {
        return client.post {
            url(PostsApi.POSTS_URL)
            contentType(ContentType.Application.Json)
            body = post
        }
    }
}