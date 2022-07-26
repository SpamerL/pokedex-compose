package com.kryak.data.di

import com.kryak.data.BuildConfig
import com.kryak.data.api.PokeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                if (!BuildConfig.DEBUG) return@apply
                addInterceptor(
                    HttpLoggingInterceptor { Timber.tag("OkHttp##\t").d(it) }
                        .apply { level = HttpLoggingInterceptor.Level.BODY }
                )
            }
            .addInterceptor(
                HttpLoggingInterceptor { message -> Timber.i(message) }.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
    }

    @Provides
    @Singleton
    fun providePokeApi(retrofit: Retrofit.Builder): PokeApi =
        retrofit.baseUrl(BASE_URL).build().create(PokeApi::class.java)
}
