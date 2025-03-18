package com.raven.hackernews.domain

/**
 * Defines the contract for local storage of stories.
 * - Stores, retrieves, and deletes stories locally.
 * - Retrieves only non-deleted stories.
 */
interface StoryLocalDataSource {
    /**
     * Retrieves a list of stories that have not been marked as deleted.
     * @return A list of non-deleted stories.
     */
    suspend fun getNonDeletedStories(page: Int = 1, queryTerm: String): List<Story>

    /**
     * Persists a list of stories locally.
     * @param stories The list of stories to be stored locally.
     */
    suspend fun createStories(stories: List<Story>, queryTerm: String)

    /**
     * Marks a story as deleted by updating its state.
     * @param storyId The ID of the story to be marked as deleted.
     */
    suspend fun deleteStory(storyId: String)
}