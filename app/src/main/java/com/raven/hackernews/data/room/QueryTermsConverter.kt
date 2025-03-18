package com.raven.hackernews.data.room

import androidx.room.TypeConverter

class QueryTermsConverter {
    @TypeConverter
    fun fromList(queryTerms: List<String>): String {
        return queryTerms.joinToString(",")
    }

    @TypeConverter
    fun toList(queryTerms: String): List<String> {
        return queryTerms.split(",").map { it.trim() }
    }
}