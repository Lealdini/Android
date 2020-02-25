package com.fabio.android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabio.android.model.DogBreed

class DetailViewModel:ViewModel() {
    val dogLiveData =  MutableLiveData<DogBreed>()
    fun fetch(){
        val dog = DogBreed("1","Corgi","15 Years",
            "breedGroup","bredFor","temperament","image")
        dogLiveData.value = dog
    }
}