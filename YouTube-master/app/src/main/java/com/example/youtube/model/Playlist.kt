package com.example.youtube.model

import androidx.room.PrimaryKey
import com.example.youtube.model2.Item
import com.example.youtube.model2.PageInfo

data class Playlist(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val etag: String?, // 63AGyPEfefXwgymKfyOyTryAQeU
    val items: List<Item>? = null,
    val kind: String?, // youtube#playlistListResponse
    val nextPageToken: String?, // CAcQAA
    val pageInfo: PageInfo?
) {
}