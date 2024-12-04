package com.nurullahsevinckan.artbookpro.repo

import androidx.lifecycle.LiveData
import com.nurullahsevinckan.artbookpro.model.ImageResponse
import com.nurullahsevinckan.artbookpro.roomdb.Art
import com.nurullahsevinckan.artbookpro.util.Resource

interface ArtRepositoryInterface { // We made this class to make code testable

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun  searchImage(imageString: String) : Resource<ImageResponse>
}