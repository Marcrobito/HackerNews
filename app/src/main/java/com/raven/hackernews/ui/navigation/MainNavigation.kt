package com.raven.hackernews.ui.navigation

import DetailScreen
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raven.hackernews.ui.screens.StoryScreen
import com.raven.hackernews.viewmodels.StoriesViewModel

@Composable
fun MainNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "story_screen"
    ) {
        composable("story_screen") { backStackEntry ->
            val viewModel: StoriesViewModel = hiltViewModel(backStackEntry)
            StoryScreen(
                storyViewModel = viewModel,
                onStoryClicked = { url ->
                    navController.navigate("detail_screen/${Uri.encode(url)}")
                }
            )
        }
        composable("detail_screen/{url}") { backStackEntry ->
            backStackEntry.arguments?.getString("url")?.let { url ->
                DetailScreen(url, onBack = {
                    navController.popBackStack()
                })
            }
        }
    }
}