package com.lijewski.sample.di

import com.lijewski.sample.BuildConfig
import com.lijewski.sample.common.PIXABAY_API_KEY
import com.lijewski.sample.common.PIXABAY_BASE_URL
import com.lijewski.sample.data.api.PixabayApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    /**
     * Provides LoggingInterceptor for api information
     */
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        } else {
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.NONE }
        }
    }

    /**
     * Provides custom OkkHttp
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @Named(PIXABAY_API_KEY) apiKey: String,
    ): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter("key", apiKey)
                    .build()
                chain.proceed(
                    chain.request().newBuilder().url(url)
                        .build()
                )
            }
            .addInterceptor(loggingInterceptor)

        return okHttpClient.build()
    }

    /**
     * Provides converter factory for retrofit
     */
    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    /**
     * Provides ApiServices client for Retrofit
     */
    @Singleton
    @Provides
    fun provideRetrofitClient(
        @Named(PIXABAY_BASE_URL) baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Singleton
    @Provides
    @Named(PIXABAY_BASE_URL)
    fun provideBaseApiUrl(): String {
        return BuildConfig.PIXABAY_BASE_URL
    }

    @Singleton
    @Provides
    @Named(PIXABAY_API_KEY)
    fun provideApiKey(): String {
        return BuildConfig.PIXABAY_API_KEY
    }

    /**
     * Provides Firmware Api Service using retrofit
     */
    @Singleton
    @Provides
    fun providePixabayApiService(retrofit: Retrofit): PixabayApi {
        return retrofit.create(PixabayApi::class.java)
    }
}