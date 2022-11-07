package com.example.rickandmortymvvmapplication.api

import com.example.rickandmortymvvmapplication.model.responseModel.GetCharacterByIdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("character/{character-id}")
    suspend fun getCharacterById(
        @Path("character-id") characterId:Int
    ):Response<GetCharacterByIdResponse>
}