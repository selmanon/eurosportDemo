package com.tech.demo.fragment

import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.tech.demo.R

class VideoDetails : Fragment(R.layout.video_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val videoView = view.findViewById<VideoView>(R.id.videoView)
        videoView.setVideoPath(requireActivity().intent.getStringExtra("video"))
        val mediaController = MediaController(requireContext())
        mediaController?.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.start()
    }
}