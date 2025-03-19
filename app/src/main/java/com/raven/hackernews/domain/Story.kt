package com.raven.hackernews.domain

data class Story (
    val id:String,
    val title:String,
    val author:String,
    val url:String,
    val createdAt:Long
)