package com.tech.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tech.demo.model.NewsModel
import com.tech.demo.model.StoryModel
import com.tech.demo.model.VideoModel

const val VIDEO_VIEW = 0
const val STORY_VIEW = 1

class NewsAdapter(private val models: List<NewsModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class VideoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.videoTitle)
        val views = view.findViewById<TextView>(R.id.views)
        val sport = view.findViewById<TextView>(R.id.video_sport)
        val image = view.findViewById<ImageView>(R.id.videoImage)
    }

    class StoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.storyTitle)
        val author = view.findViewById<TextView>(R.id.authorAndDate)
        val sport = view.findViewById<TextView>(R.id.story_sport)
        val image = view.findViewById<ImageView>(R.id.storyImage)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            STORY_VIEW -> StoryViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.story_cell_layout, parent, false)
            )
            VIDEO_VIEW -> VideoViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.video_cell_layout, parent, false)
            )
            else -> throw IllegalStateException("View Type not found")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (models[position]) {
            is VideoModel -> VIDEO_VIEW
            is StoryModel -> STORY_VIEW
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = models[position]
        when (holder) {
            is VideoViewHolder -> {
                with(item as VideoModel) {
                    holder.title.text = this.videoTitle
                    holder.views.text = this.videoViews.toString() + " views"
                    holder.sport.text = this.videoSport
                    Glide.with(holder.view).load(this.videoImage).into(holder.image)
                }

            }
            is StoryViewHolder -> {
                with(item as StoryModel) {
                    holder.title.text = this.storyTitle
                    holder.author.text = this.storyAuthor + " " + this.storyDate
                    holder.sport.text = this.storySport
                    Glide.with(holder.view).load(this.storyImage).into(holder.image)
                }
            }
        }
    }

    override fun getItemCount() = models.size
}