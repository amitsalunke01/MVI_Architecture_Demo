package com.amitsalunke.mvi_architecture_demo.repository

import com.amitsalunke.mvi_architecture_demo.model.Blog
import com.amitsalunke.mvi_architecture_demo.retrofit.BlogRetrofit
import com.amitsalunke.mvi_architecture_demo.retrofit.NetworkMapper
import com.amitsalunke.mvi_architecture_demo.room.BlogDao
import com.amitsalunke.mvi_architecture_demo.room.CacheMapper
import com.amitsalunke.mvi_architecture_demo.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.Exception

class MainRepository @Inject constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val networkMapper: NetworkMapper,
    private val cacheMapper: CacheMapper
) {
    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)//for this practice purpose not to be used in practical project
        try {
            val networkBlogsEntitys = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogsEntitys)
            for (blog in blogs) {
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cacheBlogsEntitys = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cacheBlogsEntitys)))
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }

}