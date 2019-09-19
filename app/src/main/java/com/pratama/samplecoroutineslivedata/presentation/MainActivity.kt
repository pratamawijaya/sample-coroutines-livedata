package com.pratama.samplecoroutineslivedata.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import com.pratama.samplecoroutineslivedata.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val albumViewModel: AlbumViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        albumViewModel.currentAlbum.observe(this, Observer { albums ->
            if (albums.isEmpty()) {
                Log.e("tag", "album kosong")
                loading.visibility = VISIBLE
            } else {
                loading.visibility = GONE
                tvAlbumFetched.text = "size : ${albums.size}"
                albums.map { album ->
                    Log.d("tag", "data -> ${album.name} ${album.images[3]}")
                }
            }
        })
    }
}
