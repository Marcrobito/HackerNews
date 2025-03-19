package com.raven.hackernews.data

import com.raven.hackernews.domain.Story
import com.raven.hackernews.domain.StoryDataSource
import com.raven.hackernews.domain.StoryRepository

class StoryRepositoryImpl(private val storyDataSource: StoryDataSource) : StoryRepository {


    override suspend fun getStories(queryTerm: String, page: Int): List<Story> =
        storyDataSource.getStories(page, queryTerm)


    override suspend fun deleteStory(storyId: String) {
        storyDataSource.deleteStory(storyId)
    }

}