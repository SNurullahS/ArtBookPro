package com.nurullahsevinckan.artbookpro.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nurullahsevinckan.artbookpro.ArtBookApplication
import com.nurullahsevinckan.artbookpro.R
import com.nurullahsevinckan.artbookpro.api.RetrofitAPI
import com.nurullahsevinckan.artbookpro.repo.ArtRepository
import com.nurullahsevinckan.artbookpro.repo.ArtRepositoryInterface
import com.nurullahsevinckan.artbookpro.roomdb.ArtDao
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
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )

    @Singleton
    @Provides
    fun injectNormalRepo(dao :ArtDao, api : RetrofitAPI) =  ArtRepository(dao,api) as ArtRepositoryInterface




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