package com.example.homework3.ui.detailsPlayList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homework3.App.Companion.repository
import com.example.homework3.core.base.BaseViewModel
import com.example.homework3.core.network.Resourse
import com.example.homework3.data.model.PlayListsModel
import com.example.homework3.data.model.playlistIem.PlaylistVideos
import com.example.homework3.repository.Repository

class DetailsViewModel: BaseViewModel() {

    private val repo = Repository()
    var livedata : LiveData<Resourse<PlaylistVideos>> = MutableLiveData()

    fun getPlayListItems(id : String){
        livedata = repo.getPlayListItems(id)
    }
}