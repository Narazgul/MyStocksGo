package com.example.mystocksgo.di

import com.example.mystocksgo.BuildConfig
import com.example.mystocksgo.data.api.MyStocksGoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMyStocksGoApi(retrofit: Retrofit): MyStocksGoApi =
        retrofit.create(MyStocksGoApi::class.java)
}