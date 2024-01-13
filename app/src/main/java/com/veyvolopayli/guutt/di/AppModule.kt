package com.veyvolopayli.guutt.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.veyvolopayli.guutt.common.Constants
import com.veyvolopayli.guutt.data.data_source.DayDatabase
import com.veyvolopayli.guutt.data.remote.GuuTtApi
import com.veyvolopayli.guutt.data.repository.DayRepositoryImpl
import com.veyvolopayli.guutt.data.repository.MainRepositoryImpl
import com.veyvolopayli.guutt.data.repository.PrefsRepositoryImpl
import com.veyvolopayli.guutt.domain.repository.MainRepository
import com.veyvolopayli.guutt.domain.repository.PrefsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideDayDB(application: Application): DayDatabase {
        return Room.databaseBuilder(
            application,
            DayDatabase::class.java,
            DayDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDayRepository(db: DayDatabase): DayRepositoryImpl {
        return DayRepositoryImpl(db.dayDao)
    }

    @Provides
    @Singleton
    fun provideGuuApi(): GuuTtApi {
        return Retrofit
            .Builder()
            .baseUrl(Constants.LOCALHOST)
            .client(OkHttpClient.Builder().followRedirects(true).followSslRedirects(true).build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().disableHtmlEscaping().create()))
            .build()
            .create(GuuTtApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMainRepository(api: GuuTtApi): MainRepository {
        return MainRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPrefsRepository(prefs: SharedPreferences): PrefsRepository {
        return PrefsRepositoryImpl(prefs)
    }
}