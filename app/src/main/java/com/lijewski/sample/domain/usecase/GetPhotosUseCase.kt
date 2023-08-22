package com.lijewski.sample.domain.usecase

import androidx.paging.PagingData
import com.lijewski.sample.data.model.PixabayPhoto
import com.lijewski.sample.di.IoDispatcher
import com.lijewski.sample.domain.repository.PhotosRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPhotosUseCase @Inject constructor(
    private val repository: PhotosRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
){
    fun invoke(): Flow<PagingData<PixabayPhoto>> {
        return repository.getPhotos().flowOn(ioDispatcher)
    }
}
