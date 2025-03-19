package com.raven.hackernews.data

import com.raven.hackernews.data.network.StoryDto
import com.raven.hackernews.data.room.StoryEntity
import com.raven.hackernews.domain.Story
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField

fun Story.toStoryEntity(queryTerm: String) = StoryEntity(
    id, title, author, url, createdAt, queryTerms = listOf(queryTerm)
)

fun StoryEntity.toStory() = Story(
    id, title, author, url, createdAt
)

fun StoryDto.toStory() = Story(
    id, title, author, url ?: storyUrl ?: "", createdAt.toTimestamp()
)

fun List<StoryEntity>.toStoryList() = this.map { it.toStory() }

fun List<StoryDto>.toStory() = this.map { it.toStory() }

fun String.toTimestamp(): Long = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(this))
    .getLong(ChronoField.INSTANT_SECONDS) * 1000
