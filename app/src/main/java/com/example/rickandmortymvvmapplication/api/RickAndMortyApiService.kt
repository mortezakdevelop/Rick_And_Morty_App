package com.example.rickandmortymvvmapplication.api

import retrofit2.Call
import retrofit2.http.GET

interface RickAndMortyApiService {

    @GET("character/1")
    fun getCharacterById():Call<Any>
}