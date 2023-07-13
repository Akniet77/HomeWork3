package com.example.homework3.ui.detailsPlayList

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.example.homework3.core.base.BaseActivity
import com.example.homework3.core.network.Resourse
import com.example.homework3.databinding.ActivityDetailsPlayListBinding
import com.example.homework3.ui.detailsPlayList.adapter.DetailsAdapter
import com.example.homework3.ui.playlist.gone
import com.example.homework3.ui.playlist.showToast
import com.example.homework3.ui.playlist.visible
import com.example.homework3.ui.videoActivity.VideoActivity

class DetailsPlayListActivity : BaseActivity<ActivityDetailsPlayListBinding>(ActivityDetailsPlayListBinding::inflate), DetailsAdapter.SendVideoId{

    private val viewModel by lazy { ViewModelProvider(this)[DetailsViewModel::class.java] }
    private lateinit var adapter: DetailsAdapter
    override fun inflateViewBinding() = ActivityDetailsPlayListBinding.inflate(layoutInflater)

    override fun initUI() {
        adapter = DetailsAdapter(this)
        binding.rvPlayer.adapter = adapter

        if (intent != null) {
            val id = intent?.getStringExtra("id").toString()
            viewModel.getPlayListItems(id)
            viewModel.livedata.observe(this) {
                when (it.status) {
                    Resourse.Status.SUCCESS -> {
                        it.data?.items?.let { it1 -> adapter.setPlayList(it1) }
                        binding.progress.gone()
                    }
                    Resourse.Status.ERROR ->{
                        binding.progress.gone()
                        showToast(it.message + " ")
                    }
                    Resourse.Status.LOADING -> binding.progress.visible()
                }
            }
        }

        binding.txtBack.setOnClickListener {
            onDestroy()
        }
    }

    override fun sendVideoId(videoId: String, title: String, desc: String) {
        if (videoId.isNotBlank()){
            val intent = Intent(this, VideoActivity::class.java)
            intent.putExtra("videoId", videoId)
            intent.putExtra("title", title)
            intent.putExtra("desc", desc)
            startActivity(intent)
            finish()
        }
    }
}