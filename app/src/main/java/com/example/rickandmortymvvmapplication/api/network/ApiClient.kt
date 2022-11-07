package com.example.rickandmortymvvmapplication.api.network

import com.example.rickandmortymvvmapplication.api.ApiService
import com.example.rickandmortymvvmapplication.model.responseModel.GetCharacterByIdResponse
import retrofit2.Response

class ApiClient(
    val apiService:ApiService
) {
    suspend fun getCharacterById(characterId:Int):Response<GetCharacterByIdResponse>{
        return apiService.getCharacterById(characterId)
    }
}