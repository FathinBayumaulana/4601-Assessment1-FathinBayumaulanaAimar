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

private const val BASE_URL = "https://ghastly-delicate-dragon.ngrok-free.app/link/files/Bayu/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FaunaApiService {
    @GET(".php")
    suspend fun getFauna(
        @Header("Authorization") userId: String
    ): List<Fauna>
    @GET(".php")
    suspend fun getAllFauna(

    )
    @Multipart
    @POST(".php")
    suspend fun postFauna(
        @Header("Authorization") userId: String,
        @Field("nama") nama: RequestBody,
        @Field("kingdom") kingdom: RequestBody,
        @Field("makan") makan: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus
    @DELETE(".php")
    suspend fun deleteFauna(
        @Header("Authorization") userId: String,
        @Query("id") id: Long
    ): OpStatus
}

object FaunaApi {
    val service: FaunaApiService by lazy {
        retrofit.create(FaunaApiService::class.java)
    }
    fun getFaunaUrl(imageUrl: String): String {
        return "${BASE_URL}image.php?id=$imageUrl"
    }
}

enum class FaunaStatus {
    LOADING,
    SUCCESS,
    FAILED
}