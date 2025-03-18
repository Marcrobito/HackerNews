package com.raven.hackernews.domain

/**
 * Defines the contract for fetching stories from a remote API.
 * - Always retrieves the full list of stories.
 * - Does not manage deletions.
 */
interface StoryFetchDataSource {
    /**
     * Fetches a list of stories from the API.
     * @param page The page number to fetch (default is 1).
     * @return A list of stories from the API.
     */
    suspend fun fetchStories(page: Int = 1, queryTerm: String): List<Story>
}