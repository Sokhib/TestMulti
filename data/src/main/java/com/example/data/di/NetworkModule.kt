package com.example.data.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.data.BuildConfig
import com.example.data.helper.NetworkHelper
import com.example.data.helper.NoConnectivityException
import com.example.data.services.NYTService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @NoConnectivityInterceptor
    fun providesNetworkStateInterceptor(networkHelper: NetworkHelper): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            if (!networkHelper.isNetworkConnected()) {
                throw NoConnectivityException()
            }
            val request: Request = chain.request().newBuilder().build()
            chain.proceed(request)
        }
    }

    @Provides
    @KeyInterceptor
    fun provideKeyInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            var original: Request = chain.request()
            val url = original.url.newBuilder()
                .addQueryParameter(API_KEY, "rc5Jljnq92X5JKGCWVGg3ebvlGFH5QZe").build()
            original = original.newBuilder().url(url).build()
            chain.proceed(original)
        }
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @KeyInterceptor interceptor: Interceptor,
        @NoConnectivityInterceptor noConnectivityInterceptor: Interceptor
    ) =
        if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(interceptor)
                .addInterceptor(noConnectivityInterceptor)
                .build()
        } else OkHttpClient.Builder()
            .addInterceptor(noConnectivityInterceptor)
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.nytimes.com/")
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): NYTService = retrofit.create(NYTService::class.java)

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

}

const val API_KEY = "api-key"

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class NoConnectivityInterceptor

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class KeyInterceptor

