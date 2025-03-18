package com.raven.hackernews.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoryDto(
    @Json(name = "story_id") val id: String,
    @Json(name = "story_title") val title: String,
    @Json(name = "author") val author: String,
    @Json(name = "story_url") val url: String,
    @Json(name = "created_at") val createdAt: String
)