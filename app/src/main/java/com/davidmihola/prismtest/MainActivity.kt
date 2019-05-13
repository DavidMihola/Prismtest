package com.davidmihola.prismtest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playerViewState: PlayerViewState = Fullscreen(uiVisible = true).toggleUiVisible()

        findViewById<TextView>(R.id.textView).text = playerViewState.toString()
    }
}
