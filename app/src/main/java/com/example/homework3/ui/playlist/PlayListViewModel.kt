package com.example.homework3.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homework3.core.base.BaseViewModel
import com.example.homework3.core.network.Resourse
import com.example.homework3.data.model.PlayListsModel
import com.example.homework3.repository.Repository

class PlayListViewModel: BaseViewModel() {


    private val repo = Repository()
    var livedata : LiveData<Resourse<PlayListsModel>> = MutableLiveData()

    fun getPlayList() {
        livedata = repo.getPlayLists()
    }
}