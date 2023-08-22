package com.lijewski.sample.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PixabayPhoto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user")
    val userName: String?,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("previewURL")
    val previewURL: String?,
) : Parcelable
