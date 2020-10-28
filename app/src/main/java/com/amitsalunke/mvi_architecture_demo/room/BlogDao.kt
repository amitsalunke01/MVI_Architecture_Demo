package com.amitsalunke.mvi_architecture_demo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amitsalunke.mvi_architecture_demo.model.Blog

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogCacheEntity: BlogCacheEntity): Long //it will return long value of the row (in which row of the db inserted) if it fail to insert it will return negative 1

    @Query("SELECT * FROM  blogs")
    suspend fun get(): List<BlogCacheEntity>

}