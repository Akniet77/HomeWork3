package com.example.homework3.ui.detailsPlayList.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.homework3.data.model.playlistIem.Item
import com.example.homework3.databinding.ItemPlayListVideoBinding
import com.example.homework3.ui.playlist.loadImage

class DetailsAdapter(private val sendVideoId: SendVideoId)
    : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {

    private var list: List<Item> = ArrayList()

    fun setPlayList(list: List<Item>){
        this.list = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val binding =
            ItemPlayListVideoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return DetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnClickListener {
            val videoId = list[position].contentDetails.videoId
            val videoTitle = list[position].snippet.title
            val videoDesc = list[position].snippet.description
            sendVideoId.sendVideoId(videoId, videoTitle, videoDesc)
        }
    }

    override fun getItemCount() = list.size

    class DetailsViewHolder(private val binding: ItemPlayListVideoBinding) : ViewHolder(binding.root) {
        fun onBind(item: Item) {
            binding.imgVideo.loadImage(item.snippet.thumbnails.medium.url)
            binding.tvVideoDes.text = item.snippet.title
        }

    }

    interface SendVideoId {
        fun sendVideoId(videoId: String, title: String, desc: String)
    }
}
