import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3028.assessment1.model.Fauna
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

private const val BASE_URL = "https://fenris-api-host.000webhostapp.com/files/Bayu%20Maul/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

data class FaunaResponse(
    val results: List<Fauna>
)

interface FaunaServices {
    @Multipart
    @POST("add_fauna.php")
    suspend fun addFauna(
        @Part("email") email: RequestBody,
        @Part("namaFauna") namaFauna: RequestBody,
        @Part("kingdom") kingdom: RequestBody,
        @Part("makan") makan: RequestBody,
        @Part imageUrl: MultipartBody.Part?
    ): FaunaResponse

    @POST("delete_fauna.php")
    suspend fun deleteFauna(@Part("id") id: RequestBody): FaunaResponse

    @GET("get_fauna.php")
    suspend fun getAllFauna(): FaunaResponse
}

object FaunaAPI {
    val retrofitService: FaunaServices by lazy {
        retrofit.create(FaunaServices::class.java)
    }

    fun imgUrl(imageId: String): String {
        return "$BASE_URL$imageId"
    }
}

enum class FaunaStatus { LOADING, SUCCESS, FAILED }