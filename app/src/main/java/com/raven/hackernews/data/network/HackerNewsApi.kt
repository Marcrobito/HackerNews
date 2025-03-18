package com.raven.hackernews.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface HackerNewsApi {
    /**
     * Fetches stories from the API based on the specified page number.
     * @param page The page number to fetch (default is 1).
     * @return A [StoriesResponseDto] containing the list of stories.
     */
    @GET("search_by_date")
    suspend fun getStories(
        @Query("tags") tags: String = "story",
        @Query("page") page: Int = 1
    ): StoriesResponseDto
}