package com.lijewski.sample.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PixabayList(
    @SerializedName("hits")
    val photos: List<PixabayPhoto>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int
) : Parcelable
