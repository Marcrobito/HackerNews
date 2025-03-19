package com.raven.hackernews.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raven.hackernews.domain.Story
import com.raven.hackernews.domain.StoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoriesViewModel @Inject constructor(private val storyRepository: StoryRepository) :
    ViewModel() {

    private val _state = MutableStateFlow<HackerNewsState>(HackerNewsState.Empty)
    val state: StateFlow<HackerNewsState> get() = _state

    private val currentPage = MutableStateFlow(1)

    fun onUserAction(action: HackerNewsActions) {
        when (action) {
            is HackerNewsActions.SearchTerm -> searchStories(action.term)
            is HackerNewsActions.RefreshStories -> searchStories(action.term)
            is HackerNewsActions.LoadNextStories -> searchStories(action.term, currentPage.value)
            is HackerNewsActions.DeleteStory -> viewModelScope.launch {
                _state.value = HackerNewsState.Searching(_state.value.stories)
                storyRepository.deleteStory(action.story.id)
                println(_state.value.stories.size)
                _state.value = HackerNewsState.Loaded(
                    _state.value.stories.filter { it.id != action.story.id }
                )
                println(_state.value.stories.size)
            }
        }
    }

    private fun searchStories(queryTerm: String, page: Int = 1) {
        _state.value = HackerNewsState.Searching(_state.value.stories)
        viewModelScope.launch {
            var result = mutableListOf<Story>()
            if(page > 1){
                result = _state.value.stories.toMutableList()
            }
            result += storyRepository.getStories(queryTerm, page)
            _state.value = HackerNewsState.Loaded(result)
        }

        currentPage.value = page + 1
    }

}