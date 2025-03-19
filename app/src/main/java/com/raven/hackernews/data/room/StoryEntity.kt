package com.raven.hackernews.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "stories")
@TypeConverters(QueryTermsConverter::class)
data class StoryEntity (
    @PrimaryKey val id: String,
    val title: String,
    val author: String,
    val url: String,
    val createdAt: Long,
    val isDeleted: Boolean = false,
    val queryTerms: List<String>
)