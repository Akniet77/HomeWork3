package com.example.homework3.data.remote

import com.example.homework3.data.model.PlayListsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServise {

    @GET("playlists")
    fun getPlayLists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults : Int = 10
    ) : Call<PlayListsModel>
}