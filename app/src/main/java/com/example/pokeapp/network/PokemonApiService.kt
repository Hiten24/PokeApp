package com.example.pokeapp.network

//import retrofit2.converter.gson.GsonConverterFactory
import com.example.pokeapp.util.Constant.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val client = OkHttpClient.Builder()
    .addInterceptor { chain ->
        chain.proceed(
            chain.request()
                .newBuilder()
                .header("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
                .build()
        )
    }
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(client)
    .build()

object PokemonApiService {
    val pokemonApi: PokemonApi by lazy {
        retrofit.create(PokemonApi::class.java)
    }
}
