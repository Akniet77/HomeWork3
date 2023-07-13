package com.example.homework3.ui.videoActivity

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.media3.common.Player
import com.example.homework3.core.base.BaseActivity
import com.example.homework3.databinding.ActivityVideoBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class VideoActivity : BaseActivity<ActivityVideoBinding>(ActivityVideoBinding::inflate), Player.Listener{

    override fun inflateViewBinding() = ActivityVideoBinding.inflate(layoutInflater)

    private var id : String? = null
    private var title: String? = null
    private var desc: String? = null
    private val url = "https://www.youtube.com/watch?v=HRVzCj6NegA&list=PL4jCE87BZuLx_xyUCS_18s32YoBSz31AZ&index=6"


    override fun initUI() {
        val extras = intent.extras
        id = extras?.getString("videoId")
        title = extras?.getString("title")
        desc = extras?.getString("desc")
        id?.let { initYoutubePlayer(it) }
        binding.tvTitle.text = title
        binding.tvDes.text = desc

        binding.btnDownload.setOnClickListener {
            downloadFile(this, url,"")
        }
    }
    private fun initYoutubePlayer(id: String) {
        lifecycle.addObserver(binding.youtubePlayer)
        binding.youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(id, 0f)
            }
        })
    }

    fun downloadFile(context: Context, downloadUrl: String, fileName: String) {
        val request = DownloadManager.Request(Uri.parse(downloadUrl))
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }
}