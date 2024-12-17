package com.nurullahsevinckan.artbookpro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurullahsevinckan.artbookpro.model.ImageResponse
import com.nurullahsevinckan.artbookpro.repo.ArtRepositoryInterface
import com.nurullahsevinckan.artbookpro.roomdb.Art
import com.nurullahsevinckan.artbookpro.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(
    private val repository : ArtRepositoryInterface
) : ViewModel() {

    // Art Fragment

    val artList = repository.getArt()

    // Image API Fragment

    private val images = MutableLiveData<Resource<ImageResponse>>()
    val getImageList : LiveData<Resource<ImageResponse>>
            get() = images

    private val selectedImage = MutableLiveData<String>()
    val getSelectedImageUrl : LiveData<String>
        get() = selectedImage


    // Art Details Fragments

    private var insertArtMessage = MutableLiveData<Resource<Art>>()
    val getInsertArtMessage: LiveData<Resource<Art>>
        get() = insertArtMessage

    fun resetInsertArtMessage(){
        insertArtMessage = MutableLiveData<Resource<Art>>()
    }

    fun setSelectedImage(url : String){
        selectedImage.postValue(url)
    }

    fun deleteArt(art : Art) = viewModelScope.launch{
        repository.deleteArt(art)
    }

    fun insertArt(art : Art) = viewModelScope.launch{
        repository.insertArt(art)
    }

    fun makeArt(name: String,artName :String, year : String){
        if(name.isEmpty() || artName.isEmpty() || year.isEmpty()){
            insertArtMessage.postValue(Resource.error("Enter name,art name or year!", null))
        }

        val yearToInt = try {
            year.toInt()
        }catch (e : Exception){
            insertArtMessage.postValue(Resource.error("Year is not valid!",null))
            return
        }
        val art = Art(name,artName,yearToInt,selectedImage.value ?: "")
        insertArt(art)
        setSelectedImage("")
        insertArtMessage.postValue(Resource.success(art))
    }

    fun searchForImage(searchString : String){
        if(searchString.isEmpty()){
            return
        }
        images.value  = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.searchImage(searchString)
            images.value = response
        }
    }
}