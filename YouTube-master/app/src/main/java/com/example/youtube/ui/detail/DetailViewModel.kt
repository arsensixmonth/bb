package com.example.youtube.ui.detail

import com.example.youtube.base.BaseViewModel
import com.example.youtube.data.remote.Repository

class DetailViewModel(private val repo:Repository): BaseViewModel() {


    fun  getPlaylistItem(id:String)=repo.getPlayListItems(id)


}