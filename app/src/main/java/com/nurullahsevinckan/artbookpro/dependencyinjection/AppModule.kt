package com.nurullahsevinckan.artbookpro.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.nurullahsevinckan.artbookpro.ArtBookApplication
import com.nurullahsevinckan.artbookpro.api.RetrofitAPI
import com.nurullahsevinckan.artbookpro.roomdb.ArtDatabase
import com.nurullahsevinckan.artbookpro.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(ArtBookApplication::class)
object AppModule {
    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,ArtDatabase::class.java,"ArtBookDB"

    ).build()

    @Singleton
    @Provides
    fun injectDao(database:ArtDatabase)= database.artDao()

    fun injectRetrofitAPI(): RetrofitAPI{
        return Retrofit.Builder()
               .addConverterFactory(GsonConverterFactory.create())
               .baseUrl(BASE_URL)
               .build()
               .create(RetrofitAPI::class.java)
    }
}