package com.pratama.samplecoroutineslivedata.data.datasource.remote

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val newUrl = req.url.newBuilder()
            .addQueryParameter("api_key", "70696db59158cb100370ad30a7a705c1")
            .addQueryParameter("format", "json")
            .build()
        req = req.newBuilder().url(newUrl).build()
        return chain.proceed(req)
    }
}