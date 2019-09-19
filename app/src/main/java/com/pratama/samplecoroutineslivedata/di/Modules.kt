package com.pratama.samplecoroutineslivedata.di

import com.pratama.samplecoroutineslivedata.data.DataSource
import com.pratama.samplecoroutineslivedata.data.DefaultDataSource
import com.pratama.samplecoroutineslivedata.presentation.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    single { DefaultDataSource(Dispatchers.IO) as DataSource }
    viewModel { MainViewModel(dataSource = get()) }
}