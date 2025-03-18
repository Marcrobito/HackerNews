package com.raven.hackernews.data.datasource

import com.raven.hackernews.data.room.StoryDao
import com.raven.hackernews.data.toStoryEntity
import com.raven.hackernews.data.toStoryList
import com.raven.hackernews.domain.Story
import com.raven.hackernews.domain.StoryLocalDataSource

class RoomStoryDeletionDataSourceImpl(private val storiesDao: StoryDao) : StoryLocalDataSource {
    override suspend fun getNonDeletedStories(page: Int, queryTerm: String): List<Story> {
        return storiesDao.getAllStories(
            queryTerm = queryTerm,
            pageSize = 20,
            offset = (page - 1) * 20
        ).toStoryList()
    }

    override suspend fun createStories(stories: List<Story>, queryTerm: String) {
        for (story in stories) {
            storiesDao.insertStory(story.toStoryEntity(queryTerm))
        }
    }

    override suspend fun deleteStory(storyId: String) {
        storiesDao.deleteStory(storyId)
    }
}