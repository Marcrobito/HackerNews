package com.raven.hackernews.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoriesResponseDto(
    @Json(name = "page") val page: Int,
    @Json(name = "hits") val stories: List<StoryDto>
)