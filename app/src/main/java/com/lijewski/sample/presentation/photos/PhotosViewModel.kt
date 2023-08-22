package com.lijewski.sample.presentation.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lijewski.sample.data.model.PixabayPhoto
import com.lijewski.sample.domain.usecase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {
    private val _photosPagingData =
        MutableStateFlow<PagingData<PixabayPhoto>>(PagingData.empty())
    val photosPagingData = _photosPagingData
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), PagingData.empty())

    init {
        loadPhotos()
    }

    private fun loadPhotos() {
        viewModelScope.launch {
            getPhotosUseCase.invoke().cachedIn(viewModelScope).cachedIn(viewModelScope).collect {
                _photosPagingData.value = it
            }
        }
    }

    fun onPhotoClick(photo: PixabayPhoto) {
        //TODO: display photo fullscreen?
    }
}
