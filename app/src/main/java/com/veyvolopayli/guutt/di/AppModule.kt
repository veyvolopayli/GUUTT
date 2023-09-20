package com.veyvolopayli.guutt.di

import android.app.Application
import androidx.room.Room
import com.veyvolopayli.guutt.data.data_source.DayDatabase
import com.veyvolopayli.guutt.data.repository.DayRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

    fun provideDayRepository(db: DayDatabase): DayRepositoryImpl {
        return DayRepositoryImpl(db.dayDao)
    }
}