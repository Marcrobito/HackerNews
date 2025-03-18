package com.raven.hackernews.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface StoryDao {

    /**
     * Inserts a new story into the database.
     * If a conflict occurs (same `id` exists), it will **ignore** the new entry.
     * @param story The story to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStory(story: StoryEntity)

    /**
     * Updates an existing story in the database.
     * Typically used when modifying story details or appending new query terms.
     * @param story The updated story entity.
     */
    @Update
    suspend fun updateStory(story: StoryEntity)

    /**
     * Retrieves a specific story by its ID.
     * @param storyId The ID of the story to retrieve.
     * @return The matching [StoryEntity], or `null` if not found.
     */
    @Query("SELECT * FROM stories WHERE id = :storyId LIMIT 1")
    suspend fun getStoryById(storyId: String): StoryEntity?

    /**
     * Inserts or updates a story while ensuring a query term is included.
     * - If the story already exists, it appends `queryTerm` only if it's missing.
     * - If the story does not exist, it inserts a new entry with the provided query term.
     *
     * @param story The story entity to be stored.
     * @param queryTerm The search term to associate with the story.
     */
    suspend fun storeStoryWithQuery(story: StoryEntity, queryTerm: String) {
        val existingStory = getStoryById(story.id)

        if (existingStory != null) {
            // Append query term only if it's not already in the list
            if (!existingStory.queryTerms.contains(queryTerm)) {
                val updatedStory = existingStory.copy(
                    queryTerms = existingStory.queryTerms + queryTerm
                )
                updateStory(updatedStory) // Update existing story in the database
            }
        } else {
            // Insert new story with the query term
            val newStory = story.copy(queryTerms = listOf(queryTerm))
            insertStory(newStory)
        }
    }

    /**
     * Marks a story as deleted by setting `isDeleted = 1` (logical deletion).
     * This allows the story to be excluded from future queries without actually removing it from the database.
     *
     * @param storyId The ID of the story to be marked as deleted.
     */
    @Query("UPDATE stories SET isDeleted = 1 WHERE id = :storyId")
    suspend fun deleteStory(storyId: String)

    /**
     * Retrieves all non-deleted stories (isDeleted = 0), filtered by a search term, with pagination.
     *
     * - Filters out logically deleted stories (`isDeleted = 0`).
     * - Matches stories where `queryTerms` contains `queryTerm` (partial match).
     * - Orders results by `createdAt` in descending order (newest first).
     * - Supports pagination using `LIMIT` and `OFFSET`.
     *
     * @param queryTerm The search term to filter stories by.
     * @param pageSize The number of stories to return per page (default: 20).
     * @param offset The number of stories to skip for pagination (default: 0).
     * @return A paginated list of non-deleted stories matching the search term.
     */
    @Query(
        """
        SELECT * FROM stories 
        WHERE isDeleted = 0 
        AND queryTerms LIKE '%' || :queryTerm || '%' 
        ORDER BY createdAt DESC 
        LIMIT :pageSize OFFSET :offset
        """
    )
    suspend fun getAllStories(
        queryTerm: String,
        pageSize: Int = 20,
        offset: Int = 0
    ): List<StoryEntity>
}