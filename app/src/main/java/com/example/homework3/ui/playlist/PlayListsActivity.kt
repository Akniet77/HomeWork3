package com.example.homework3.ui.playlist

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework3.core.base.BaseActivity
import com.example.homework3.core.network.Resourse
import com.example.homework3.data.model.PlayListsModel
import com.example.homework3.databinding.ActivityMainBinding

class PlayListsActivity : BaseActivity<ActivityMainBinding>(){

    private val viewModel by lazy { ViewModelProvider(this)[PlayListViewModel::class.java] }
    override fun inflateViewBinding() = ActivityMainBinding.inflate(layoutInflater)
    private val adapter: PlayListAdapter by lazy { PlayListAdapter() }


    override fun initUI() {
        binding.recyclerView.adapter = adapter
        viewModel.getPlayList()
        viewModel.livedata.observe(this) {
            when (it.status) {
                Resourse.Status.SUCCESS ->{
                    val playListsModel = it.data
                    val data = playListsModel?.items ?: emptyList()
                    adapter.setData(ArrayList(data))
                }
                Resourse.Status.ERROR -> it.message
                Resourse.Status.LOADING -> this.showToast("Loading")
            }
        }

    }
}