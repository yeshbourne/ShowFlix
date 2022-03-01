package com.yb.showflix.di

import androidx.viewbinding.BuildConfig
import com.squareup.moshi.Moshi
import com.yb.showflix.service.ShowsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptor {
            val request = it.request()
            val httpUrl = request.url.newBuilder()
                .addQueryParameter("apikey","6554cc44")
                .build()
            val newRequest = request.newBuilder()
                .url(httpUrl)
                .build()

         it.proceed(newRequest)
        })
        .build()

    @Provides
    @Singleton
     fun providesRetrofitService(retrofit: Retrofit): ShowsService =
        retrofit.create(ShowsService::class.java)

    @Provides
    @Singleton
     fun provideRetrofitBuilder(): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.omdbapi.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}