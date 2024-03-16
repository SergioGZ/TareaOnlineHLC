package com.sergio.apitarea4

import retrofit2.Call
import retrofit2.http.GET

interface DigimonApi {
    @GET("/api/v1/digimon?page=2")
    fun getDigimons(): Call<DigimonResponse>
}
