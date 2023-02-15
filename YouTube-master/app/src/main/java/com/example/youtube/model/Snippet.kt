package com.example.youtube.model

data class Snippet(
    var channelId: String?,
    var channelTitle: String?,
    var description: String?,
    var localized: Localized?,
    var publishedAt: String?,
    var thumbnails: Thumbnails?,
    var title: String?
)