package com.example.riverstone.repository

import kotlinx.coroutines.flow.Flow
import com.example.riverstone.common.Resource
import com.example.riverstone.network.remote.dto.PostDto
import com.example.riverstone.model.PostModel

interface PostsRepository {

    fun getPosts(): Flow<Resource<List<PostModel>>>

    fun postPost(post: PostDto): Flow<Resource<PostDto>>
}