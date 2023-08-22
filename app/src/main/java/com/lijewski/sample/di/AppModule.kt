package com.lijewski.sample.di

import com.lijewski.sample.data.api.PixabayApi
import com.lijewski.sample.data.repository.LocalMessageRepository
import com.lijewski.sample.data.repository.MockAuthRepository
import com.lijewski.sample.data.repository.PixabayPhotosRepository
import com.lijewski.sample.data.room.TextMessageDao
import com.lijewski.sample.domain.repository.AuthRepository
import com.lijewski.sample.domain.repository.MessageRepository
import com.lijewski.sample.domain.repository.PhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository {
        return MockAuthRepository()
    }

    @Provides
    @Singleton
    fun providePhotosRepository(pixabayApi: PixabayApi): PhotosRepository {
        return PixabayPhotosRepository(pixabayApi)
    }

    @Provides
    @Singleton
    fun providesMessageRepository(
        textMessageDao: TextMessageDao,
        authRepository: AuthRepository,
    ): MessageRepository {
        return LocalMessageRepository(textMessageDao, authRepository)
    }
}
