package com.lijewski.sample.presentation.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lijewski.sample.data.model.PixabayPhoto
import com.lijewski.sample.domain.usecase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
): ViewModel() {
    fun getRemoteData(): Flow<PagingData<PixabayPhoto>> = getPhotosUseCase.invoke().cachedIn(viewModelScope)

    fun onPhotoClick(photo: PixabayPhoto) {
        //TODO: display photo fullscreen?
    }
}
