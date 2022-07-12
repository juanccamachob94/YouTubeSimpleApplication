package com.example.youtubesimpleapplication

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import io.github.cdimascio.dotenv.Dotenv

class MainActivity : YouTubeBaseActivity() {
    private lateinit var youTubePlayer: YouTubePlayerView
    private lateinit var youTubePlayerInit: YouTubePlayer.OnInitializedListener

    private lateinit var btnPlayYouTube: Button
    private lateinit var btnTesting: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.youTubePlayer = findViewById(R.id.youTubePlayer)
        this.youTubePlayerInit = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo(DotenvFactory.getInstance()["YOU_TUBE_VIDEO_ID"])
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
            }
        }

        this.btnPlayYouTube = findViewById(R.id.btnPlayYouTube)
        this.btnPlayYouTube.setOnClickListener {
            this.youTubePlayer.initialize(DotenvFactory.getInstance()["YOU_TUBE_API_KEY"], youTubePlayerInit)
        }

        this.btnTesting = findViewById(R.id.btnTesting)
        this.btnTesting.setOnClickListener {
            if(DotenvFactory.getInstance() == null)
                println("get instance es nula")
            else {
                println("get instance sí tiene valor")
                println(DotenvFactory.getInstance()["YOU_TUBE_API_KEY"])
            }
        }
    }
}