package com.raven.hackernews.data.datasource

import com.raven.hackernews.domain.Story
import com.raven.hackernews.domain.StoryDataSource
import com.raven.hackernews.domain.StoryFetchDataSource
import com.raven.hackernews.domain.StoryLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StoryDataSourceImpl @Inject constructor(
    private val localDataSource: StoryLocalDataSource,
    private val storyFetchDataSource: StoryFetchDataSource
) : StoryDataSource {
    override suspend fun getStories(page: Int, queryTerm: String): List<Story> {
        val fetchedStories = withContext(Dispatchers.IO) {
            storyFetchDataSource.fetchStories(page, queryTerm)
        }
        withContext(Dispatchers.IO) {
            localDataSource.createStories(fetchedStories, queryTerm)
        }
        val stories = withContext(Dispatchers.IO) {
            localDataSource.getNonDeletedStories(page, queryTerm)
        }
        return stories
    }

    override suspend fun deleteStory(storyId: String) {
        localDataSource.deleteStory(storyId)
    }
}