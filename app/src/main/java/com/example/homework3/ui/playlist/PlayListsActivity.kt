package com.example.homework3.ui.playlist

import android.content.Intent
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.homework3.core.base.BaseActivity
import com.example.homework3.core.network.Resourse
import com.example.homework3.databinding.ActivityMainBinding
import com.example.homework3.ui.detailsPlayList.DetailsPlayListActivity
import com.example.homework3.ui.internetCheck.CheckInternet


class PlayListsActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val viewModel by lazy { ViewModelProvider(this)[PlayListViewModel::class.java] }

    override fun inflateViewBinding() = ActivityMainBinding.inflate(layoutInflater)
    private lateinit var adapter: PlayListAdapter
    private lateinit var checkInternet: CheckInternet

    override fun initUI() {
        adapter = PlayListAdapter(click = { id ->
            val intent = Intent(this, DetailsPlayListActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        })
        binding.recyclerView.adapter = adapter
        viewModel.getPlayList()
        getPlayListsYouTube()
        callNetworkConnection()

        binding.containerInternet.btnCheck.setOnClickListener {
            if (callNetworkConnection()) {
                startActivity(Intent(this, PlayListsActivity::class.java))
                finish()
            }
        }
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

        private fun callNetworkConnection(): Boolean {
            checkInternet = CheckInternet(application)
            checkInternet.observe(this) {
                if (it) {
                    binding.isMainActivity.isVisible = true
                    binding.containerInternet.checkInternet.isVisible = false
                } else {
                    binding.isMainActivity.isVisible = false
                    binding.containerInternet.checkInternet.isVisible = true
                }
            }
            return false
        }
    }
