package com.veyvolopayli.guutt.di

import android.app.Application
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.veyvolopayli.guutt.common.Constants
import com.veyvolopayli.guutt.data.data_source.DayDatabase
import com.veyvolopayli.guutt.data.remote.GuuApi
import com.veyvolopayli.guutt.data.repository.DayRepositoryImpl
import com.veyvolopayli.guutt.data.repository.MainRepositoryImpl
import com.veyvolopayli.guutt.domain.repository.MainRepository
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
    fun provideGuuApi(): GuuApi {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .client(OkHttpClient.Builder().followRedirects(true).followSslRedirects(true).build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().disableHtmlEscaping().create()))
            .build()
            .create(GuuApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMainRepository(api: GuuApi): MainRepository {
        return MainRepositoryImpl(api)
    }
}