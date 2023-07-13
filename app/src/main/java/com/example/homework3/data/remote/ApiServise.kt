package com.example.homework3.data.remote

import com.example.homework3.BuildConfig
import com.example.homework3.data.model.PlayListsModel
import com.example.homework3.data.model.playlistIem.PlaylistVideos
import com.example.homework3.utils.Constants
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


    @GET("playlistItems")
    fun getPLayListItems(
        @Query("part") part: String = Constants.PART,
        @Query("playlistId") id: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("maxResults") maxResults: Int = 10
    ): Call<PlaylistVideos>
}