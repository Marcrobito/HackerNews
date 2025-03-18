package com.raven.hackernews.domain

/**
 * Defines the contract for managing stories, combining both fetching and deletion operations.
 * - Fetches stories from a remote API.
 * - Retrieves the list of non-deleted stories stored locally.
 * - Marks stories as deleted (logical deletion).
 */
interface StoryDataSource {
    /**
     * Fetches a list of stories from the API, filtering out deleted ones.
     * @param page The page number to fetch (default is 1).
     * @return A list of non-deleted stories.
     */
    suspend fun getStories(page: Int = 1, queryTerm: String): List<Story>

    /**
     * Marks a story as deleted by storing its ID in the local data source.
     * @param storyId The ID of the story to be marked as deleted.
     */
    suspend fun deleteStory(storyId: String)
}