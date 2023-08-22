package com.lijewski.sample.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lijewski.sample.data.api.PixabayApi
import com.lijewski.sample.data.model.PixabayPhoto
import com.lijewski.sample.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PixabayPhotosRepository @Inject constructor(
    private val service: PixabayApi
): PhotosRepository {
    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

    override fun getPhotos(): Flow<PagingData<PixabayPhoto>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { PixabayPagingSource(service) }
        ).flow
    }
}
