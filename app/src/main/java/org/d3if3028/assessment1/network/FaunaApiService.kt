package org.d3if3028.assessment1.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3028.assessment1.model.Fauna
import org.d3if3028.assessment1.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private const val BASE_URL = "https://sapi-salto-rpla.000webhostapp.com/files/Web%20Testing/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FaunaApiService {
    @GET("test_api.php")
    suspend fun getFauna(
    ): List<Fauna>
//    @GET("api_bayu.php")
//    suspend fun getAllFauna(
//
//    )
    @Multipart
    @POST("test_api.php")
    suspend fun postFauna(
        @Part("email") userId: String,
        @Part("nama") nama: RequestBody,
        @Part("kingdom") kingdom: RequestBody,
        @Part("makan") makan: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus
    @DELETE("test_api.php")
    suspend fun deleteFauna(
        @Query("id") id: Int
    ): OpStatus
}

object FaunaApi {
    val service: FaunaApiService by lazy {
        retrofit.create(FaunaApiService::class.java)
    }
    fun getFaunaUrl(imageId: String): String {
        return "${BASE_URL}image.php?id=$imageId"
    }
}

enum class FaunaStatus {
    LOADING,
    SUCCESS,
    FAILED
}