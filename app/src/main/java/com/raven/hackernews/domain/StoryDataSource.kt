package com.raven.hackernews.domain

/**
 * Defines the contract for managing stories, combining both fetching and deletion operations.
 * - Fetches stories from a remote API.
 * - Retrieves the list of deleted stories.
 * - Marks stories as deleted (logical deletion).
 */
interface StoryDataSource {
    /**
     * Fetches a list of stories from the API, filtering out deleted ones.
     * @param page The page number to fetch (default is 1).
     * @return A list of non-deleted stories.
     */
    fun getStories(page: Int = 1): List<Story>

    /**
     * Retrieves a list of IDs of stories that have been marked as deleted.
     * @return A list of deleted story IDs.
     */
    fun getAllDeleted(): List<String>

    /**
     * Marks a story as deleted by storing its ID in the local data source.
     * @param storyId The ID of the story to be marked as deleted.
     */
    fun deleteStory(storyId: String)
}

/**
 * Defines the contract for handling logical deletions of stories.
 * - Stores and retrieves deleted story IDs.
 * - Ensures deleted stories are not permanently removed from the API.
 */
interface StoryDeletionDataSource {
    /**
     * Retrieves a list of story IDs that have been marked as deleted.
     * @return A list of deleted story IDs.
     */
    fun getAllDeleted(): List<String>

    /**
     * Marks a story as deleted by storing its ID.
     * @param storyId The ID of the story to be deleted.
     */
    fun deleteStory(storyId: String)
}

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
    fun getStories(page: Int = 1): List<Story>
}