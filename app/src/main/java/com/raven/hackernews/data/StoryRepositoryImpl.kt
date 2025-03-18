package com.raven.hackernews.data

import com.raven.hackernews.domain.Story
import com.raven.hackernews.domain.StoryDataSource
import com.raven.hackernews.domain.StoryRepository

class StoryRepositoryImpl(private val storyDataSource: StoryDataSource) : StoryRepository {

    private var page = 0
    override suspend fun getStories(queryTerm:String): List<Story> {
        val stories = storyDataSource.getStories(page, queryTerm)
        page++
        return stories
    }

    override suspend fun deleteStory(storyId: String) {
        storyDataSource.deleteStory(storyId)
    }

}