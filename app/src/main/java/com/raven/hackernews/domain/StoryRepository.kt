package com.raven.hackernews.domain

/**
 * Repository interface that serves as the main entry point for fetching and managing stories.
 * - Retrieves stories from the appropriate data source.
 * - Handles logical deletion of stories.
 * - Abstracts the underlying data sources from the ViewModel.
 */
interface StoryRepository {
    /**
     * Fetches a list of stories, applying filtering logic to exclude deleted ones.
     * @param page The page number to fetch (default is 1).
     * @return A list of non-deleted stories.
     */
    fun getStories(page: Int = 1): List<Story>

    /**
     * Marks a story as deleted by storing its ID in the local deletion tracking system.
     * @param storyId The ID of the story to be logically deleted.
     */
    fun deleteStory(storyId: String)
}

