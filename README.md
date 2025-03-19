# HackerNews

HackerNews is an Android application that allows users to browse and manage stories in a style similar to Hacker News. The app connects to the Hacker News API (or Algolia, depending on your implementation) to search and display stories using a UI built with Jetpack Compose. It also incorporates features such as infinite scrolling, pull-to-refresh, and swipe-to-dismiss for individual items.

## Features

- **Story Search & Listing:** Users can search for stories using a query term, and results are displayed in a list.
- **Infinite Scrolling & Pagination:** The list dynamically loads more stories as the user scrolls down.
- **Pull-to-Refresh:** Refresh the story list using a swipe-down gesture.
- **Swipe-to-Dismiss:** Remove individual stories by swiping them away.
- **Dependency Injection:** Uses Hilt for managing dependencies across the app.
- **Local Persistence:** Implements Room for local data storage, enabling offline support and efficient updates.
- **Modern Architecture:** Built with the MVVM pattern, separating UI logic (ViewModels) from data management (Repositories).

## Project Structure

The project is organized into several key modules and packages:

- **app/**  
  This is the main module of the application:
    - **domain:** Business rules and basic models.
    - **ui/screens:** Contains primary screens and composables, such as the `StoryScreen` which displays the list of stories.
    - **ui/components:** Reusable UI components (e.g., `StoryItem` with swipe-to-dismiss functionality).
    - **viewmodels:** Contains ViewModels (like `StoriesViewModel`) that handle UI logic and communicate with repositories.
    - **di:** Dependency injection setup using Hilt.
    - **data:** Data layer implementation, which includes:
        - **network:** Retrofit configuration (or API connectivity to Hacker News).
        - **room:** Room database configuration, entities, and DAOs.
        - **repositories:** Repository implementations (e.g., `StoryRepositoryImpl`) that orchestrate remote and local data access.

## Future Improvements
- **Unit Testing**
- **Record Last Query and display on load**
- **Handling of network errors** 
