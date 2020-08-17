package com.tech.demo.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tech.demo.R

class StoryDetails : Fragment(R.layout.story_details) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = requireActivity().intent.extras

        view.findViewById<TextView>(R.id.storyTitle).text = bundle?.getString("title")
        view.findViewById<TextView>(R.id.authorAndDate).text = bundle?.getString("author")
        view.findViewById<TextView>(R.id.teaser).text = bundle?.getString("teaser")
        view.findViewById<TextView>(R.id.story_sport).text = bundle?.getString("sport")

        Glide.with(view).load(bundle?.getString("image"))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view.findViewById<ImageView>(R.id.storyImage))
    }
}