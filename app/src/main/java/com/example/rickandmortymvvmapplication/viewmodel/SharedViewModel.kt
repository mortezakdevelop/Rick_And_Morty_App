package com.example.rickandmortymvvmapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortymvvmapplication.model.responseModel.GetCharacterByIdResponse
import com.example.rickandmortymvvmapplication.repository.SharedRepository
import kotlinx.coroutines.launch

class SharedViewModel:ViewModel() {

    private val repository = SharedRepository()

    private val _characterByIdLiveData = MutableLiveData<GetCharacterByIdResponse>()
    val characterByIdLiveData  : LiveData<GetCharacterByIdResponse> = _characterByIdLiveData

    fun refreshCharacter(characterId:Int){
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            _characterByIdLiveData.postValue(response)
        }
    }
}