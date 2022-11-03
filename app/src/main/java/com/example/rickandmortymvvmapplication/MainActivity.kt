package com.example.rickandmortymvvmapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.rickandmortymvvmapplication.api.RickAndMortyApiService
import com.example.rickandmortymvvmapplication.api.responseModel.GetCharacterByIdResponse
import com.example.rickandmortymvvmapplication.databinding.ActivityMainBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.root

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        // create instance retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(" https://rickandmortyapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        // implement service interface
        val service:RickAndMortyApiService = retrofit.create(RickAndMortyApiService::class.java)

        service.getCharacterById(8).enqueue(object :Callback<GetCharacterByIdResponse>{
            override fun onResponse(call: Call<GetCharacterByIdResponse>, response: Response<GetCharacterByIdResponse>) {
                //Toast.makeText(this@MainActivity,"this is response ---- ${response.body().toString()}",Toast.LENGTH_LONG).show()
                if(!response.isSuccessful){
                    Toast.makeText(this@MainActivity,"Response failed",Toast.LENGTH_LONG).show()
                }

                val body = response.body()
                val image = body!!.image
                binding.tvSpace.text = image

                Glide.with(this@MainActivity).load(image).into(binding.ivImage);



            }

            override fun onFailure(call: Call<GetCharacterByIdResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,"this is Error ---- ${t.message.toString()}",Toast.LENGTH_LONG).show()
            }

        })
    }
}