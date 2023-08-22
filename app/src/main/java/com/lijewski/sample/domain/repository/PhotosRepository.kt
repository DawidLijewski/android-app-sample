package com.lijewski.sample.domain.repository

import androidx.paging.PagingData
import com.lijewski.sample.data.model.PixabayPhoto
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {
    fun getPhotos(): Flow<PagingData<PixabayPhoto>>
}
