package com.example.rickandmortymvvmapplication.api

import com.example.rickandmortymvvmapplication.api.responseModel.GetCharacterByIdResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApiService {

    @GET("character/{character-id}")
    fun getCharacterById(
        @Path("character-id") characterId:Int
    ):Call<GetCharacterByIdResponse>
}