package com.lijewski.sample.di

import android.content.Context
import androidx.room.Room
import com.lijewski.sample.data.room.AppDatabase
import com.lijewski.sample.data.room.TextMessageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideIntelliDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME).build()

    @Provides
    @Singleton
    fun provideTextMessageDao(
        database: AppDatabase
    ): TextMessageDao = database.textMessageDao()
}
