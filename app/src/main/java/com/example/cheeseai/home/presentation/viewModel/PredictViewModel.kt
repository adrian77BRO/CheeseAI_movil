package com.example.cheeseai.home.presentation.viewModel

import androidx.lifecycle.*
import com.example.cheeseai.home.data.models.PredictDTO
import com.example.cheeseai.home.data.repository.PredictRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class PredictViewModel() : ViewModel() {
    private val repository = PredictRepository()

    private val _predictionResult = MutableLiveData<PredictDTO?>()
    val predictionResult: LiveData<PredictDTO?> = _predictionResult

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> = _errorMessage

    fun predict(file: MultipartBody.Part) {
        _isLoading.value = true
        _errorMessage.value = ""
        viewModelScope.launch {
            try {
                val response = repository.predictImage(file)
                if (response.isSuccessful) {
                    _predictionResult.value = response.body()
                } else {
                    _errorMessage.value = "Error en la respuesta del servidor: ${response.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error desconocido"
            } finally {
                _isLoading.value = false
            }
        }
    }
}