package com.pratama.samplecoroutineslivedata.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.pratama.samplecoroutineslivedata.data.DataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date

class MainViewModel(private val dataSource: DataSource) : ViewModel() {

    companion object {
        const val LOADING_STRING = "Loading..."
    }

    val currentTime = dataSource.getCurrentTime()

    val currentTimeTransformed = currentTime.switchMap {
        liveData { emit(timeStampToTime(it)) }
    }

    val currentWeather: LiveData<String> = liveData {
        emit(LOADING_STRING)
        emitSource(dataSource.fetchWeather())
    }

    val cachedValue = dataSource.cachedData

    fun onRefresh() {
        viewModelScope.launch {
            dataSource.fetchNewData()
        }
    }

    private suspend fun timeStampToTime(it: Long): String {
        delay(500)
        val date = Date(it)
        return date.toString()
    }
}