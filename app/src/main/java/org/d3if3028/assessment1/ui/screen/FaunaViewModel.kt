package org.d3if3028.assessment1.ui.screen

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.d3if3028.assessment1.model.Fauna
import org.d3if3028.assessment1.network.FaunaApi
import org.d3if3028.assessment1.network.FaunaStatus
import java.io.ByteArrayOutputStream

class FaunaViewModel : ViewModel() {
    var data = mutableStateOf(emptyList<Fauna>())
        private set
    var status = MutableStateFlow(FaunaStatus.LOADING)
        private set
    var errorMessage = mutableStateOf<String?>(null)
        private set
    fun retrieveData(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            status.value = FaunaStatus.LOADING
            try {
                data.value = FaunaApi.service.getFauna(userId)
                status.value = FaunaStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = FaunaStatus.FAILED
            }
        }
    }
    fun saveData(userId: String, nama: String, kingdom: String, makan: String, bitmap: Bitmap) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = FaunaApi.service.postFauna(
                    userId,
                    nama.toRequestBody("text/plain".toMediaTypeOrNull()),
                    kingdom.toRequestBody("text/plain".toMediaTypeOrNull()),
                    makan.toRequestBody("text/plain".toMediaTypeOrNull()),
                    bitmap.toMultipartBody()
                )
                if (result.status == "success")
                    retrieveData(userId)
                else
                    throw Exception(result.message)
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }
    fun deleteData(userId: String, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = FaunaApi.service.deleteFauna(userId, id)
                if (result.status == "success")
                    retrieveData(userId)
                else
                    throw Exception(result.message)
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }
    private fun Bitmap.toMultipartBody(): MultipartBody.Part {
        val stream = ByteArrayOutputStream()
        compress(Bitmap.CompressFormat.JPEG, 80, stream)
        val byteArray = stream.toByteArray()
        val requestBody = byteArray.toRequestBody(
            "image/jpg".toMediaTypeOrNull(), 0, byteArray.size
        )
        return MultipartBody.Part.createFormData(
            "image", "image.jpg", requestBody
        )
    }
    fun clearMessage() {
        errorMessage.value = null
    }
}