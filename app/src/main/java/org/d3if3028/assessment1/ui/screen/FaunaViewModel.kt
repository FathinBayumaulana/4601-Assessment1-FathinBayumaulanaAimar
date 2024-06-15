package org.d3if3028.assessment1.ui.screen

import FaunaStatus
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.d3if3028.assessment1.model.Fauna
import java.io.ByteArrayOutputStream

class FaunaViewModel : ViewModel() {
    private val _faunaData = MutableLiveData<List<Fauna>>()
    val faunaData: LiveData<List<Fauna>> get() = _faunaData

    var _status = MutableStateFlow(FaunaStatus.LOADING)
        private set
    val status: StateFlow<FaunaStatus> get() = _status

    init {
        getAllFauna()
    }

    fun getAllFauna() {
        viewModelScope.launch(Dispatchers.IO) {
            _status.value = FaunaStatus.LOADING
            try {
                val response = FaunaAPI.retrofitService.getAllFauna()
                _faunaData.postValue(response.results)
                _status.value = FaunaStatus.SUCCESS
            } catch (e: Exception) {
                _status.value = FaunaStatus.FAILED
                Log.e("FaunaViewModel", "[Get All Fauna] Error: ${e.message}")
            }
        }
    }

    fun addFauna(email: String, namaFauna: String, kingdom: String, makan: String, image: Bitmap?) {
        viewModelScope.launch(Dispatchers.IO) {
            _status.value = FaunaStatus.LOADING
            try {
                val emailPart = email.toRequestBody("text/plain".toMediaTypeOrNull())
                val namaFaunaPart = namaFauna.toRequestBody("text/plain".toMediaTypeOrNull())
                val kingdomPart = kingdom.toRequestBody("text/plain".toMediaTypeOrNull())
                val makanPart = makan.toRequestBody("text/plain".toMediaTypeOrNull())

                val imagePart: MultipartBody.Part? = image?.let {
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    it.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
                    val requestBody = RequestBody.create("image/jpeg".toMediaTypeOrNull(), byteArrayOutputStream.toByteArray())
                    MultipartBody.Part.createFormData("imageUrl", "image.jpg", requestBody)
                }

                val response = FaunaAPI.retrofitService.addFauna(emailPart, namaFaunaPart, kingdomPart, makanPart, imagePart)
                _faunaData.postValue(response.results)
                _status.value = FaunaStatus.SUCCESS
            } catch (e: Exception) {
                _status.value = FaunaStatus.FAILED
                Log.e("FaunaViewModel", "[Add Fauna] Error: ${e.message}")
            }
        }
    }

    fun deleteFauna(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _status.value = FaunaStatus.LOADING
            try {
                val idPart = id.toRequestBody("text/plain".toMediaTypeOrNull())
                val response = FaunaAPI.retrofitService.deleteFauna(idPart)
                _faunaData.postValue(response.results)
                _status.value = FaunaStatus.SUCCESS
            } catch (e: Exception) {
                _status.value = FaunaStatus.FAILED
                Log.e("FaunaViewModel", "[Delete Fauna] Error: ${e.message}")
            }
        }
    }
}