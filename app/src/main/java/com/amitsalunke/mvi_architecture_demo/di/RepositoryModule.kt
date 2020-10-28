package com.amitsalunke.mvi_architecture_demo.di

import com.amitsalunke.mvi_architecture_demo.repository.MainRepository
import com.amitsalunke.mvi_architecture_demo.retrofit.BlogRetrofit
import com.amitsalunke.mvi_architecture_demo.retrofit.NetworkMapper
import com.amitsalunke.mvi_architecture_demo.room.BlogDao
import com.amitsalunke.mvi_architecture_demo.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        blogRetrofit: BlogRetrofit,
        networkMapper: NetworkMapper,
        cacheMapper: CacheMapper
    ): MainRepository {
        return MainRepository(blogDao, blogRetrofit, networkMapper, cacheMapper)

    }
}