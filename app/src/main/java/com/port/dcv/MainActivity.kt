package com.port.dcv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.Util
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView


class MainActivity : AppCompatActivity() {
    lateinit var playerView:PlayerView
    lateinit var player:ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playerView = findViewById(R.id.player_view)
        val player = ExoPlayer.Builder(this).build()
        playerView.player = player
        val mediaItem = MediaItem.Builder()
            .setUri("https://www.dropbox.com/s/tofb56970h4vpq6/Zack.Snyders.Justice.League.2021.2160p.HMAX.WEB-DL.DDP5.1.Atmos.HDR.HEVC-EVO.mkv?raw=1")
            .build()
        val mediaSource = ProgressiveMediaSource.Factory(
            DefaultDataSource.Factory(this)
        ).createMediaSource(mediaItem)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }


}