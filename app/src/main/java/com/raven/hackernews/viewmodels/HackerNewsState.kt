package com.raven.hackernews.viewmodels

import com.raven.hackernews.domain.Story

sealed class HackerNewsState(open val stories: List<Story>) {
    data object Empty : HackerNewsState(emptyList())
    data class Searching(override val stories: List<Story>) : HackerNewsState(emptyList())
    data class Loaded(override val stories: List<Story>) :
        HackerNewsState(stories)
}