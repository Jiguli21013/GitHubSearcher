package com.yanchelenko.githubsearcher.data.remote

import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 5L

class TransportBuilder {

    private val HTTP_SCHEME = "https"
    private val API_ENDPOINT = "api.github.com/"

    fun buildRetrofit(): Retrofit {
        val client =
            OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectionSpecs(listOf(getTlsSpec()))
                .addInterceptor(getHttpLoggingInterceptor())
                .build()

        return Retrofit.Builder()
            .baseUrl("${HTTP_SCHEME}://${API_ENDPOINT}")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getTlsSpec() = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
        .tlsVersions(TlsVersion.TLS_1_2)
        .build()

    private fun getHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
