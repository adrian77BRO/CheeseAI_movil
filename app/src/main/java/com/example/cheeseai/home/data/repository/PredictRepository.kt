package com.example.cheeseai.home.data.repository

import com.example.cheeseai.core.network.RetrofitInstance
import com.example.cheeseai.home.data.models.PredictDTO
import okhttp3.MultipartBody
import retrofit2.Response

class PredictRepository {
    suspend fun predictImage(file: MultipartBody.Part): Response<PredictDTO> {
        return RetrofitInstance.predictService.predictImage(file)
    }
}