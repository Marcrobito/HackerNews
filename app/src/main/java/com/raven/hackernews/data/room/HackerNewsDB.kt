package com.raven.hackernews.data.room

import androidx.room.Database

@Database(entities = [StoryEntity::class], version = 1)
abstract class HackerNewsDB {
    abstract fun storyDao(): StoryDao
}
