package com.port.dcv

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView




class MainActivity : AppCompatActivity() {

    lateinit var playerView:PlayerView
    lateinit var player:ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val windowInsetController = WindowCompat.getInsetsController(window,window.decorView)
        val layoutParams = window.attributes
        layoutParams.screenBrightness = 1.0f
        window.attributes = layoutParams
        windowInsetController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetController.hide(WindowInsetsCompat.Type.systemBars())
        setContentView(R.layout.activity_main)
        playerView = findViewById(R.id.player_view)
       // requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE


        val player = ExoPlayer.Builder(this)
            .build()
        playerView.player = player
        playerView.setBackgroundColor(Color.BLACK)


        val mediaItem = MediaItem.Builder()
            .setUri("https://www.dropbox.com/s/tofb56970h4vpq6/Zack.Snyders.Justice.League.2021.2160p.HMAX.WEB-DL.DDP5.1.Atmos.HDR.HEVC-EVO.mkv?raw=1")
            .build()
        val mediaSource = ProgressiveMediaSource.Factory(
            DefaultDataSource.Factory(this)
        ).createMediaSource(mediaItem)

        player.setMediaItem(mediaItem)



//        player.prepare()
        player.play()
    }


}