package com.pratama.samplecoroutineslivedata.di

import com.pratama.samplecoroutineslivedata.BuildConfig
import com.pratama.samplecoroutineslivedata.data.DataSource
import com.pratama.samplecoroutineslivedata.data.DefaultDataSource
import com.pratama.samplecoroutineslivedata.data.datasource.remote.AlbumServices
import com.pratama.samplecoroutineslivedata.data.datasource.remote.MyInterceptor
import com.pratama.samplecoroutineslivedata.data.datasource.remote.mapper.AlbumRemoteMapper
import com.pratama.samplecoroutineslivedata.data.datasource.remote.mapper.ImageRemoteMapper
import com.pratama.samplecoroutineslivedata.data.datasource.remote.repository.AlbumRepositoryImpl
import com.pratama.samplecoroutineslivedata.domain.repository.AlbumRepository
import com.pratama.samplecoroutineslivedata.domain.usecase.GetAlbumByPhrase
import com.pratama.samplecoroutineslivedata.presentation.AlbumViewModel
import com.pratama.samplecoroutineslivedata.presentation.MainViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val TIMEOUT: Long = 60

val appModule: Module = module {

    single { apiServices }

    single { DefaultDataSource(Dispatchers.IO) as DataSource }
    single { ImageRemoteMapper() }
    single { AlbumRemoteMapper(imageRemoteMapper = get()) }
    single { AlbumRepositoryImpl(service = get(), albumRemoteMapper = get()) as AlbumRepository }

    single { GetAlbumByPhrase(repository = get()) }

    viewModel { MainViewModel(dataSource = get()) }
    viewModel { AlbumViewModel(useCase = get()) }
}

private val okHttpClient: OkHttpClient = createOkHttpClient(MyInterceptor(), BuildConfig.DEBUG)
private val apiServices: AlbumServices =
    createWebService(okHttpClient = okHttpClient, url = "http://ws.audioscrobbler.com/2.0/")

fun createOkHttpClient(interceptor: Interceptor?, debug: Boolean = false): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    val clientBuilder = OkHttpClient.Builder()

    if (debug) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }

    interceptor?.let {
        clientBuilder.addInterceptor(interceptor)
    }

    return clientBuilder
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}