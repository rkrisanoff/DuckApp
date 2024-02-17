package ru.sample.duckapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.sample.duckapp.domain.Duck

interface DucksApi {
    @GET("random")
    fun getRandomDuck(): Call<Duck>

    @GET("http/{number}.jpg")
    fun getHttpDuck(@Path("number") number: Int): Call<String>
}