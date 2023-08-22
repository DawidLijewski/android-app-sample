package com.lijewski.sample.data.api

import com.lijewski.sample.data.model.PixabayList
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api/")
    suspend fun getPics(
        @Query("page") page: Int
    ): PixabayList
}
