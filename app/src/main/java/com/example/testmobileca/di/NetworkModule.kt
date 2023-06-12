package com.example.testmobileca.di


import android.content.Context
import com.example.testmobileca.data.retrofit.EndpointInterceptor
import com.example.testmobileca.utils.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.testmobileca.utils.DebugLog
import com.example.testmobileca.utils.TAG
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.net.ProxySelector
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


private const val CONNECT_TIMEOUT = 15
private const val WRITE_TIMEOUT = 15
private const val READ_TIMEOUT = 15

@Qualifier
annotation class GlideScope

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                DebugLog.d(TAG, message)
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun endpointInterceptor(
        dataStoreManager: DataStoreManager,
        context: Context
    ): EndpointInterceptor {
        return EndpointInterceptor(dataStoreManager, context)
    }


    @Provides
    @Singleton
    fun okHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        endpointInterceptor: EndpointInterceptor
    ): OkHttpClient {
        val proxySelector = ProxySelector.getDefault()

        val builder = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(endpointInterceptor)
            .proxySelector(proxySelector)
            .addInterceptor(loggingInterceptor)

        return builder.build()
    }

    @Provides
    @Singleton
    fun cacheFile(context: Context): File {
        return File(context.cacheDir, "okhttp_cache")
    }

    @Provides
    @Singleton
    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, (10 * 1000 * 1000).toLong()) //10MB Cache
    }

    @Provides
    @GlideScope
    @Singleton
    fun okHttpGlideClient(
        loggingInterceptor: HttpLoggingInterceptor,
        cache: Cache,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor(loggingInterceptor)

        return builder.build()
    }
}
