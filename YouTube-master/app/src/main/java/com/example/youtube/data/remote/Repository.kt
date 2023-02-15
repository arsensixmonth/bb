package com.example.youtube.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtube.App
import com.example.youtube.model2.Playlist
import com.example.youtube.network.Resource
import kotlinx.coroutines.Dispatchers

class Repository(private val apiService: ApiService){


    fun getPlayList(): LiveData<Resource<Playlist>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val result = apiService.getPlaylist()

        if (result.isSuccessful) {
            emit(Resource.success(result.body()))
        } else {
            emit(Resource.error(result.message()))
        }
    }

    fun getPlayListItems(playlistId: String): LiveData<Resource<Playlist>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val result = apiService.getPlaylistItems(playlistId = playlistId)

            if (result.isSuccessful) {
                emit(Resource.success(result.body()))
            } else {
                emit(Resource.error(result.message()))
            }
        }

    fun setPlaylistDB(playlist: Playlist): LiveData<Resource<Boolean>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            Log.e("ololo", "setplaylist$playlist")
            App.db.dao().insertPlaylist(playlist)
            emit(Resource.success(true))


        }

    fun getPlaylistDB(): LiveData<Resource<Playlist>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val result = App.db.dao().getPlaylist()
        Log.e("ololo", "getPlaylistDB$result")
        if (result != null) {
            emit(Resource.success(result))
        } else {
            emit(Resource.error("Empty data"))
        }
    }

}