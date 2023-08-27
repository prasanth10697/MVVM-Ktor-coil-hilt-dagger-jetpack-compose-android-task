package com.example.riverstone.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.riverstone.common.Resource
import com.example.riverstone.network.PostsApi
import com.example.riverstone.network.remote.dto.PostDto
import com.example.riverstone.network.remote.dto.toPost
import com.example.riverstone.model.PostModel

class PostsRepositoryImpl(private val api: PostsApi) : PostsRepository {

    override fun getPosts(): Flow<Resource<List<PostModel>>> = flow {
        try {
            emit(Resource.Loading())
            val posts = api.getPosts().map { it.toPost() }
            emit(Resource.Success(data = posts))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "Error"))
        }
    }

    override fun postPost(post: PostDto): Flow<Resource<PostDto>> = flow {
        try {
            emit(Resource.Loading())
            val postResult = api.postPost(post)!!
            emit(Resource.Success(data = postResult))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }
    }
}