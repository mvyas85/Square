package com.challenge.square.di

import com.challenge.square.util.Constants
import com.challenge.square.util.OkHttpInterceptor
import com.challenge.square.view.MainFragmentFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ApplicationScope

    @Singleton
    @Provides
    fun provideOkHttp3Client(): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(OkHttpInterceptor()).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
    }

    @Singleton
    @Provides
    fun provideMainFragmentFactory(): MainFragmentFactory {
        return MainFragmentFactory()
    }
}
