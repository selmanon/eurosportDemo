package com.tech.demo.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tech.demo.R
import com.tech.demo.StoryDetailActivity
import com.tech.demo.VideoDetailActivity
import com.tech.demo.model.NewsModel
import com.tech.demo.model.StoryModel
import com.tech.demo.model.VideoModel
import javax.inject.Inject

const val VIDEO_VIEW = 0
const val STORY_VIEW = 1

class NewsAdapter(private val context:Context, private val models: List<NewsModel>) :
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
                    .inflate(
                        R.layout.story_cell_layout,
                        parent,
                        false
                    )
            )
            VIDEO_VIEW -> VideoViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(
                        R.layout.video_cell_layout,
                        parent,
                        false
                    )
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
                    Glide.with(holder.view).load(this.videoImage).diskCacheStrategy(
                        DiskCacheStrategy.ALL
                    ).into(holder.image)
                }

                holder.view.setOnClickListener {
                    val intent = Intent(context, VideoDetailActivity::class.java)
                    intent.apply {
                        this.putExtra("video", item.videoUrl)

                    }
                    context.startActivity(intent)

                }

            }
            is StoryViewHolder -> {
                with(item as StoryModel) {
                    holder.title.text = this.storyTitle
                    holder.author.text = this.storyAuthor + " " + this.storyDate
                    holder.sport.text = this.storySport
                    Glide.with(holder.view).load(this.storyImage)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image)

                    holder.view.setOnClickListener {
                        val intent = Intent(context, StoryDetailActivity::class.java)
                        intent.apply {
                            this.putExtra("image", item.storyImage)
                            this.putExtra("sport", item.storySport)
                            this.putExtra("title", item.storyTitle)
                            this.putExtra("author", item.storyAuthor)
                            this.putExtra("teaser", item.storyTeaser)

                        }
                        context.startActivity(intent)

                    }
                }
            }
        }
    }

    override fun getItemCount() = models.size
}