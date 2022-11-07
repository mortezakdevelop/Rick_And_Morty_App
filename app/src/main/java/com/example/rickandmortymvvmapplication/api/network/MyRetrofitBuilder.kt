package com.example.rickandmortymvvmapplication.api.network

import com.example.rickandmortymvvmapplication.api.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MyRetrofitBuilder {

    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    // create instance retrofit
     var retrofit = Retrofit.Builder()
        .baseUrl(" https://rickandmortyapi.com/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    // implement service interface without lazy
    //val service:RickAndMortyApiService = retrofit.create(RickAndMortyApiService::class.java)

    // implement service interface with lazy
    val service: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    val apiClient = ApiClient(service)
}