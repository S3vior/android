package com.example.s3vior.data.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.s3vior.data.source.remote.endPoints.MafqoudApiService
import com.example.s3vior.networking.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://s3vior22.herokuapp.com/api/")
             .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMafqoudApiService(retrofit: Retrofit): MafqoudApiService {
        return retrofit.create(MafqoudApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserApiService(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor,
        prefs: SharedPreferences
    ): OkHttpClient {
        return OkHttpClient.Builder()
//            .addNetworkInterceptor { chain ->
//                var request = chain.request()
//                if (request.header("No-Authentication") == null) {
//                    var token: String = prefs.getString("token", null) ?: ""
//                    request = request.newBuilder()
//                        .addHeader("Authorization", prefs.getString("token", null) ?: "guest")
//                        .build();
//                }
//                chain.proceed(request)
//            }
            .addInterceptor(interceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()
    }


}