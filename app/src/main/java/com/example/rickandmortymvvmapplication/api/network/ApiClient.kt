package com.example.rickandmortymvvmapplication.api.network

import com.example.rickandmortymvvmapplication.api.ApiService
import com.example.rickandmortymvvmapplication.model.responseModel.GetCharacterByIdResponse
import retrofit2.Response
import java.lang.reflect.Executable

class ApiClient(
    val apiService:ApiService
) {
    suspend fun getCharacterById(characterId:Int):SimpleResponse<GetCharacterByIdResponse>{
        return safeApiCall { apiService.getCharacterById(characterId) }
    }

    private inline fun<T> safeApiCall(apiCall: () -> Response<T>):SimpleResponse<T>{
        return try {
            SimpleResponse.success(apiCall.invoke())
        }catch (e:Exception){
            SimpleResponse.failed(e)
        }
    }
}