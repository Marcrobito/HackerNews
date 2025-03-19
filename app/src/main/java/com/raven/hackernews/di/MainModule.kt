package com.raven.hackernews.di

import android.app.Application
import androidx.room.Room
import com.raven.hackernews.data.StoryRepositoryImpl
import com.raven.hackernews.data.datasource.StoryDataSourceImpl
import com.raven.hackernews.data.datasource.StoryFetchDataSourceImpl
import com.raven.hackernews.data.datasource.StoryLocalDataSourceImpl
import com.raven.hackernews.data.network.HackerNewsApi
import com.raven.hackernews.data.network.NetworkService
import com.raven.hackernews.data.room.HackerNewsDB
import com.raven.hackernews.data.room.StoryDao
import com.raven.hackernews.domain.StoryDataSource
import com.raven.hackernews.domain.StoryFetchDataSource
import com.raven.hackernews.domain.StoryLocalDataSource
import com.raven.hackernews.domain.StoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    fun providesHackerNewsApi() = NetworkService.service

    @Provides
    fun providesHackerNewsDataBase(application: Application) = Room.databaseBuilder(
        application,
        HackerNewsDB::class.java, "database"
    ).build()

    @Provides
    fun providesStoriesDao(db: HackerNewsDB) = db.storyDao()

    @Provides
    fun providesStoryFetchDataSource(api: HackerNewsApi): StoryFetchDataSource =
        StoryFetchDataSourceImpl(api)

    @Provides
    fun providesStoryLocalDataSource(storyDao: StoryDao): StoryLocalDataSource =
        StoryLocalDataSourceImpl(storyDao)

    @Provides
    fun providesStoryDataSource(
        storyFetchDataSource: StoryFetchDataSource,
        storyLocalDataSource: StoryLocalDataSource
    ): StoryDataSource = StoryDataSourceImpl(storyLocalDataSource, storyFetchDataSource)

    @Provides
    fun providesStoryRepository(storyDataSource: StoryDataSource): StoryRepository =
        StoryRepositoryImpl(storyDataSource)
}