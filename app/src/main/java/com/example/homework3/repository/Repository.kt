package com.example.homework3.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homework3.BuildConfig
import com.example.homework3.core.network.Resourse
import com.example.homework3.core.network.RetrofitClient
import com.example.homework3.data.model.PlayListsModel
import com.example.homework3.data.remote.ApiServise
import com.example.homework3.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository {

    private val apiService : ApiServise = RetrofitClient.create()

    fun getPlayLists() : LiveData<Resourse<PlayListsModel>>{
        val data  = MutableLiveData<Resourse<PlayListsModel>>()

        data.value = Resourse.loading()

        apiService.getPlayLists(
            part = Constants.PART,
            channelId = Constants.CHANNEL_ID,
            apiKey = BuildConfig.API_KEY,
            maxResults = 10,

        ).enqueue(object : Callback<PlayListsModel> {
            override fun onResponse(
                call: Call<PlayListsModel>,
                response: Response<PlayListsModel>
            ) {
                if (response.isSuccessful){
                    data.value = Resourse.success(response.body())
                }
            }

            override fun onFailure(call: Call<PlayListsModel>, t: Throwable) {
                Resourse.error(t.message.toString(), null)
            }
        })
        return data
    }
}