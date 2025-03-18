package com.raven.hackernews.data.datasource

import com.raven.hackernews.data.network.HackerNewsApi
import com.raven.hackernews.data.toStory
import com.raven.hackernews.domain.Story
import com.raven.hackernews.domain.StoryFetchDataSource
import javax.inject.Inject

class StoryFetchDataSourceImpl @Inject constructor(private val hackerNewsApi: HackerNewsApi) :
    StoryFetchDataSource {

    override suspend fun fetchStories(page: Int, queryTerm: String): List<Story> =
        hackerNewsApi.getStories(queryTerm, page).stories.toStory()

}