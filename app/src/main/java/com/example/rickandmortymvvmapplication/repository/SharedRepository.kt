package com.example.rickandmortymvvmapplication.repository

import com.example.rickandmortymvvmapplication.api.network.MyRetrofitBuilder
import com.example.rickandmortymvvmapplication.model.responseModel.GetCharacterByIdResponse

class SharedRepository {

    suspend fun getCharacterById(characterId:Int):GetCharacterByIdResponse?{

        val request = MyRetrofitBuilder.apiClient.getCharacterById(characterId)

        if (request.isSuccessful){
            return request.body()
        }
        return null
    }
}