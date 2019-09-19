package com.pratama.samplecoroutineslivedata.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface DataSource {
    fun getCurrentTime(): LiveData<Long>
    fun fetchWeather(): LiveData<String>
    val cachedData: LiveData<String>
    suspend fun fetchNewData()
}


class DefaultDataSource(private val ioDispatcher: CoroutineDispatcher) : DataSource {
    override fun getCurrentTime(): LiveData<Long> = liveData {
        while (true) {
            emit(System.currentTimeMillis())
            delay(1000)
        }
    }


    // Exposes a LiveData of changing weather conditions, every 2 seconds.
    private val weatherConditions = listOf("Sunny", "Cloudy", "Rainy", "Stormy", "Snowy")

    override fun fetchWeather(): LiveData<String> {
        var counter = 0
        return liveData {
            while (true) {
                counter++
                delay(2000)
                emit(weatherConditions[counter % weatherConditions.size])
            }
        }
    }

    private val _cachedData = MutableLiveData("old data")
    override val cachedData: LiveData<String> = _cachedData

    override suspend fun fetchNewData() {
        withContext(Dispatchers.Main) {
            _cachedData.value = "Fetching new data"
            _cachedData.value = simulatedNetworkFetch()
        }
    }

    private var counter = 0
    private suspend fun simulatedNetworkFetch(): String = withContext(ioDispatcher) {
        delay(3000)
        counter++
        "New data"
    }
}