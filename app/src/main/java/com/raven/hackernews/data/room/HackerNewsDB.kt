package com.raven.hackernews.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StoryEntity::class], version = 1)
abstract class HackerNewsDB : RoomDatabase() {
    abstract fun storyDao(): StoryDao
}
