package com.example.homework3.ui.playlist

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.homework3.core.base.BaseActivity
import com.example.homework3.core.network.Resourse
import com.example.homework3.databinding.ActivityMainBinding
import com.example.homework3.ui.internetCheck.CheckInternet

class PlayListsActivity : BaseActivity<ActivityMainBinding>(),CheckInternet.InternetCheckListener {

    private val viewModel by lazy { ViewModelProvider(this)[PlayListViewModel::class.java] }
    override fun inflateViewBinding() = ActivityMainBinding.inflate(layoutInflater)
    private val adapter: PlayListAdapter by lazy { PlayListAdapter() }


    override fun initUI() {
        binding.recyclerView.adapter = adapter
        viewModel.getPlayList()
        getPlayListsYouTube()


    }

    fun getPlayListsYouTube() {
        viewModel.livedata.observe(this) {
            when (it.status) {
                Resourse.Status.SUCCESS -> {
                    val playListsModel = it.data
                    val data = playListsModel?.items ?: emptyList()
                    adapter.setData(ArrayList(data))
                }
                Resourse.Status.ERROR -> it.message
                Resourse.Status.LOADING -> this.showToast("Loading")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        internetCheck.register(this)
    }

    override fun onPause() {
        super.onPause()
        internetCheck.unregister()
    }

    override fun onInternetAvailable() {
        runOnUiThread {
            binding.nooo.visibility = View.GONE

        }
    }

    override fun onInternetLost() {
        runOnUiThread {
            binding.noConnection.visibility = View.VISIBLE
        }
    }
}