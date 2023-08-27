package com.example.riverstone.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import com.example.riverstone.network.PostsApi
import com.example.riverstone.network.PostsApiImpl
import com.example.riverstone.repository.PostsRepositoryImpl
import com.example.riverstone.repository.PostsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    @Provides
    @Singleton
    fun providePostsApi(client: HttpClient): PostsApi = PostsApiImpl(client)

    @Provides
    fun providePostsRepository(api: PostsApi): PostsRepository = PostsRepositoryImpl(api)
}