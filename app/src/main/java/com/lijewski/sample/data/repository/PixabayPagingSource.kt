package com.lijewski.sample.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lijewski.sample.data.api.PixabayApi
import com.lijewski.sample.data.model.PixabayPhoto
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PixabayPagingSource @Inject constructor(
    private val service: PixabayApi
) : PagingSource<Int, PixabayPhoto>() {
    companion object {
        private const val PIXABAY_STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PixabayPhoto> {
        val position = params.key ?: PIXABAY_STARTING_PAGE_INDEX
        return try {
            val response = service.getPics(position)
            val photos = response.photos
            Log.d("Zivi", "Service -> getPhotos: ${photos.size}")
            LoadResult.Page(
                data = photos,
                prevKey = if (position == PIXABAY_STARTING_PAGE_INDEX) null else position,
                nextKey = if (photos.isEmpty()) null else position + 1

            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PixabayPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
