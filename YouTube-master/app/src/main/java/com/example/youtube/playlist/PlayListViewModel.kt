package com.example.youtube.playlist


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.youtube.base.BaseViewModel
import com.example.youtube.data.remote.Repository
import com.example.youtube.model.Playlist
import com.example.youtube.network.Resource

class PlayListViewModel(private val repo:Repository) : BaseViewModel() {


    val setPlaylistLiveData = MutableLiveData<Playlist>()

    val setPlaylistDB = setPlaylistLiveData.switchMap { repo.setPlaylistDB(it) }

    val getPlaylist: LiveData<Resource<Playlist>> = repo.getPlayList()

    val getPlaylistDB: LiveData<Resource<Playlist>> = repo.getPlaylistDB()

    fun setPlaylistDB(playlist: Playlist) {
        setPlaylistLiveData.postValue(playlist)
    }


}