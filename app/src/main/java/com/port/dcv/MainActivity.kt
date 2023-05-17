package com.port.dcv

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.port.dcv.databinding.ActivityMainBinding
import com.port.dcv.databinding.ActivityHdrPopupBinding

class MainActivity : AppCompatActivity() {
    private var playWhenReady = true
    private var mediaItemIndex = 0
    private var playbackPosition = 0L
    private var player: ExoPlayer? = null
    private val viewBinding by lazy(LazyThreadSafetyMode.NONE){
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val hdrView by lazy(LazyThreadSafetyMode.NONE){
        ActivityHdrPopupBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        viewBinding.playerView.setBackgroundColor(Color.BLACK)
        val layoutParams = window.attributes
        layoutParams.screenBrightness = 1.0f
        val hdrLabel = hdrView.textView

        hdrLabel.text = ""
    }


    override fun onStart(){
        super.onStart()
        initializePlayer()
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if (player == null){
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }
    private val urlOfVideo = "https://www.dropbox.com/s/tofb56970h4vpq6/Zack.Snyders.Justice.League.2021.2160p.HMAX.WEB-DL.DDP5.1.Atmos.HDR.HEVC-EVO.mkv?raw=1"
    private fun initializePlayer(){
        player = ExoPlayer.Builder(this)
            .build()
            .also{exoPlayer -> viewBinding.playerView.player = exoPlayer
                val mediaItem = MediaItem.fromUri(urlOfVideo)
            exoPlayer.setMediaItem(mediaItem)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(mediaItemIndex,playbackPosition)
                exoPlayer.prepare()

            }


    }
    private fun releasePlayer() {
        player?.let { player ->
            playbackPosition = player.currentPosition
            mediaItemIndex = player.currentMediaItemIndex
            playWhenReady = player.playWhenReady
            player.release()
        }
        player = null
    }
    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, viewBinding.playerView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }


}