package com.example.youtubeapiexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayerView
const val VIDEO_ID = "jkJmngd4L3g"

class MainActivity : YouTubeBaseActivity(), OnInitializedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = layoutInflater.inflate(R.layout.activity_main, null) as ConstraintLayout
        setContentView(layout)

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        p1?.setPlayerStateChangeListener(playerStateChangeListener)
        p1?.setPlaybackEventListener(playerPlaybackEventListener)

        if (!p2){
            p1?.cueVideo(VIDEO_ID)
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        val REQ_CODE = 0

        if(p1?.isUserRecoverableError == true){
            p1.getErrorDialog(this, REQ_CODE).show()
        } else{
            val errMsg = "Error during YouTubePlayer initialisation ($p1)"
            Toast.makeText(this, errMsg, Toast.LENGTH_LONG).show()
        }
    }

    private val playerStateChangeListener = object: YouTubePlayer.PlayerStateChangeListener{
        override fun onLoading() {
            //TODO("Not yet implemented")
        }

        override fun onLoaded(p0: String?) {

        }

        override fun onAdStarted() {

        }

        override fun onVideoStarted() {
            Toast.makeText(this@MainActivity, "Video is started", Toast.LENGTH_SHORT).show()
        }

        override fun onVideoEnded() {
            Toast.makeText(this@MainActivity, "Video is ended", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {

        }

    }

    private val playerPlaybackEventListener = object: YouTubePlayer.PlaybackEventListener{
        override fun onPlaying() {
            Toast.makeText(this@MainActivity, "Video is playing on", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@MainActivity, "Video is paused", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {
            Toast.makeText(this@MainActivity, "Video is stopped", Toast.LENGTH_SHORT).show()
        }

        override fun onBuffering(p0: Boolean) {

        }

        override fun onSeekTo(p0: Int) {

        }

    }
}