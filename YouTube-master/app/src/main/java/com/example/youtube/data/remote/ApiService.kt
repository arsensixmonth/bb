package com.example.youtube.data.remote


import com.example.youtube.BuildConfig
import com.example.youtube.`object`.Constant
import com.example.youtube.model2.Playlist
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlists")
    suspend fun getPlaylist(
        @Query("part") part: String = Constant.PART,
        @Query("channelId") channelId: String = Constant.CHANNEL_ID,
        @Query("maxResults") maxResults: String = Constant.MAX_RESULTS,
        @Query("key") key: String = BuildConfig.API_KEY,
    ): Response<Playlist>

    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("part") part: String = Constant.PART,
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResults: String = Constant.MAX_RESULTS,
    ): Response<Playlist>
}