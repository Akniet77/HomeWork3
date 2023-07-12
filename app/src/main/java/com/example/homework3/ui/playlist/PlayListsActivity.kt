package com.example.homework3.ui.playlist

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.example.homework3.core.base.BaseActivity
import com.example.homework3.core.network.Resourse
import com.example.homework3.databinding.ActivityMainBinding
import com.example.homework3.ui.internetCheck.CheckInternet
import com.example.homework3.ui.internetCheck.CheckInternetActivity


class PlayListsActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel by lazy { ViewModelProvider(this)[PlayListViewModel::class.java] }

    override fun inflateViewBinding() = ActivityMainBinding.inflate(layoutInflater)
    private val adapter: PlayListAdapter by lazy { PlayListAdapter() }
    private lateinit var checkInternet: CheckInternet

    override fun initUI() {
        binding.recyclerView.adapter = adapter
        viewModel.getPlayList()
        getPlayListsYouTube()
        callNetworkConnection()

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

    private fun callNetworkConnection(){
        checkInternet = CheckInternet(application)
        checkInternet.observe(this) { isConnected ->
            if (!isConnected) {
               val intent  = Intent(this, CheckInternetActivity::class.java )
                startActivity(intent)
                finish()
            }

        }
    }
}