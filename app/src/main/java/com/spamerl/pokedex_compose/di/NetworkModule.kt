package com.spamerl.pokedex_compose.di

import android.content.Context
import com.spamerl.pokedex_compose.BuildConfig
import com.spamerl.pokedex_compose.data.remote.api.PokeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                if (!BuildConfig.DEBUG) return@apply
                addInterceptor(
                    HttpLoggingInterceptor { Timber.tag("OkHttp##\t").d(it) }
                        .apply { level = HttpLoggingInterceptor.Level.BODY }
                )
            }
            .cache(Cache(context.cacheDir, 5L * 1024 * 1024))
            .addInterceptor { forceCache(it) }
            .build()

    private fun forceCache(it: Interceptor.Chain, day: Int = 7): Response {
        val request = it.request().newBuilder().header(
            "Cache-Control",
            "max-stale=" + 60 * 60 * 24 * day
        ).build()
        val response = it.proceed(request)
        Timber.d("provideOkHttpClient: response: $response")
        Timber.i("provideOkHttpClient: cacheControl: ${response.cacheControl}")
        Timber.i("provideOkHttpClient: networkResponse: ${response.networkResponse}")
        return response
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit.Builder): PokeApi =
        retrofit.baseUrl(PokeApi.BASE_URL).build().create(PokeApi::class.java)
}