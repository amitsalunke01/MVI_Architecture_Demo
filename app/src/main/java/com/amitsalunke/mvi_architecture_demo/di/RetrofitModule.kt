package com.amitsalunke.mvi_architecture_demo.di

import com.amitsalunke.mvi_architecture_demo.model.Blog
import com.amitsalunke.mvi_architecture_demo.retrofit.BlogNetworkEntity
import com.amitsalunke.mvi_architecture_demo.retrofit.BlogRetrofit
import com.amitsalunke.mvi_architecture_demo.util.EntityMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)//application wide dependency
object RetrofitModule {
    /* @Singleton
     @Provides
     fun providesNetworkMapper():EntityMapper<BlogNetworkEntity,Blog>{
         return
     }*/

    @Singleton
    @Provides
    fun providesGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()// we can remove this if we remove @expose from BlogNetworkEntity
            .create()
    }

    @Singleton
    @Provides
    fun providesRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://open-api.xyz/placeholder/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun providesBlogService(retrofit: Retrofit.Builder): BlogRetrofit {
        return retrofit
            .build()
            .create(BlogRetrofit::class.java)

    }
}