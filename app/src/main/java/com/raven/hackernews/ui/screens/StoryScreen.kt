package com.raven.hackernews.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.raven.hackernews.ui.components.StoryList
import com.raven.hackernews.viewmodels.HackerNewsActions
import com.raven.hackernews.viewmodels.HackerNewsState
import com.raven.hackernews.viewmodels.StoriesViewModel

@Composable
fun StoryScreen(storyViewModel: StoriesViewModel = hiltViewModel()) {
    var queryTerm by remember { mutableStateOf("") }
    val state by storyViewModel.state.collectAsState()
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    value = queryTerm,
                    onValueChange = {
                        queryTerm = it
                    },
                    enabled = state !is HackerNewsState.Searching
                )
                Spacer(modifier = Modifier.width(16.dp))
                IconButton({
                    storyViewModel.onUserAction(HackerNewsActions.SearchTerm(queryTerm))
                }) {
                    Icon(Icons.Filled.Search, "")
                }
            }
            StoryList(state.stories, onClick = { story ->
                story.url
            }, onDelete = { story ->
                storyViewModel.onUserAction(HackerNewsActions.DeleteStory(story = story))
            }, onRefresh = {
                storyViewModel.onUserAction(HackerNewsActions.RefreshStories(queryTerm))
            }) {
                storyViewModel.onUserAction(HackerNewsActions.LoadNextStories(queryTerm))
            }
        }
        if (state is HackerNewsState.Searching) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
fun StoryScreen_Preview() {
    StoryScreen()
}