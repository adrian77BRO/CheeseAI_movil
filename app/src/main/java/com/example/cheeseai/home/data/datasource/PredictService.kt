package com.example.cheeseai.home.data.datasource

import com.example.cheeseai.home.data.models.PredictDTO
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PredictService {
    @Multipart
    @POST("predict")
    suspend fun predictImage(@Part file: MultipartBody.Part): Response<PredictDTO>
}