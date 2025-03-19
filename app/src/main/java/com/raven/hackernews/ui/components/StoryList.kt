package com.raven.hackernews.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.raven.hackernews.domain.Story
import com.raven.hackernews.viewmodels.HackerNewsActions
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoryList(
    stories: List<Story>,
    onDelete: (Story) -> Unit = {},
    onClick: (Story) -> Unit = {},
    onRefresh: () -> Unit = {},
    onListEndReached: () -> Unit = {}
) {
    PullToRefreshBox(
        isRefreshing = false,
        onRefresh = onRefresh,
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(stories, key = { _, story -> story.id + Date().time }) { index, story ->
                StoryItem(story = story, onDelete = onDelete) {
                    onClick(story)
                }
                if (index == stories.lastIndex) {
                    LaunchedEffect(key1 = stories) {
                        onListEndReached()
                    }
                }
            }
        }
        /*
        items(items = stories, key = { it.id }) { story ->
        StoryItem(story = story, onDelete = { onDelete(story) }, onClick = { onClick(story) })
    }
         */
    }
}