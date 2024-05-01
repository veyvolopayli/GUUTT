package com.veyvolopayli.guutt.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.veyvolopayli.guutt.common.Constants
import com.veyvolopayli.guutt.common.serializers.LocalDateGsonSerializer
import com.veyvolopayli.guutt.common.serializers.LocalDateSerializer
import com.veyvolopayli.guutt.data.data_source.ClassesDatabase
import com.veyvolopayli.guutt.data.data_source.NotesDatabase
import com.veyvolopayli.guutt.data.remote.GuuTtApi
import com.veyvolopayli.guutt.data.repository.AuthRepositoryImpl
import com.veyvolopayli.guutt.data.repository.ClassNotesRepositoryImpl
import com.veyvolopayli.guutt.data.repository.DbClassesRepositoryImpl
import com.veyvolopayli.guutt.data.repository.MainRepositoryImpl
import com.veyvolopayli.guutt.data.repository.NewsRepositoryImpl
import com.veyvolopayli.guutt.data.repository.PrefsRepositoryImpl
import com.veyvolopayli.guutt.domain.repository.AuthRepository
import com.veyvolopayli.guutt.domain.repository.ClassNotesRepository
import com.veyvolopayli.guutt.domain.repository.DbClassesRepository
import com.veyvolopayli.guutt.domain.repository.MainRepository
import com.veyvolopayli.guutt.domain.repository.NewsRepository
import com.veyvolopayli.guutt.domain.repository.PrefsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideDayDB(application: Application): ClassesDatabase {
        return Room.databaseBuilder(
            application,
            ClassesDatabase::class.java,
            ClassesDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNotesDb(application: Application): NotesDatabase {
        return Room.databaseBuilder(
            application,
            NotesDatabase::class.java,
            NotesDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideClassNotesRepository(notesDatabase: NotesDatabase): ClassNotesRepository {
        return ClassNotesRepositoryImpl(notesDatabase.notesDao)
    }

    @Provides
    @Singleton
    fun provideDbClassesRepository(db: ClassesDatabase): DbClassesRepository {
        return DbClassesRepositoryImpl(db.classesDao)
    }

    @Provides
    @Singleton
    fun provideGuuApi(): GuuTtApi {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .client(OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .build())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient().disableHtmlEscaping()
                        .create()
                )
            )
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

    @Provides
    @Singleton
    fun provideNewsRepository(api: GuuTtApi): NewsRepository {
        return NewsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: GuuTtApi): AuthRepository {
        return AuthRepositoryImpl(api)
    }
}