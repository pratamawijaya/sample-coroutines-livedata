package com.pratama.samplecoroutineslivedata.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.pratama.samplecoroutineslivedata.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.currentTimeTransformed.observe(this, Observer {
            currentTime.text = it
        })

        viewModel.currentWeather.observe(this, Observer {
            Log.d("tag", "current weather value $it")
        })
    }
}
