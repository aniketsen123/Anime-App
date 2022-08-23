package service

import com.example.anime.SearchedAnime
import com.example.anime.Searching
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface anime_searching {

    @GET("search")
    fun searching(@Query("keyw")queryString: String): Call<Searching>
    companion object {
        private const val BASE_URL = "https://gogoanime.herokuapp.com/"

        fun create(): anime_searching {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(anime_searching::class.java)
        }
    }

}