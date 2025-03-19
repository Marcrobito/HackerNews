package com.raven.hackernews.viewmodels

import com.raven.hackernews.domain.Story

sealed class HackerNewsActions {
    data class SearchTerm(val term:String):HackerNewsActions()
    data class LoadNextStories(val term:String):HackerNewsActions()
    data class RefreshStories(val term:String):HackerNewsActions()
    data class DeleteStory(val story:Story):HackerNewsActions()
}