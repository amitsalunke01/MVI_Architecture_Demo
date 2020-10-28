package com.amitsalunke.mvi_architecture_demo.room

import com.amitsalunke.mvi_architecture_demo.model.Blog
import com.amitsalunke.mvi_architecture_demo.util.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor() : EntityMapper<BlogCacheEntity, Blog> {
    override fun mapFromEntity(entity: BlogCacheEntity): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )
    }

    override fun mapToEntity(domain: Blog): BlogCacheEntity {
        return BlogCacheEntity(
            id = domain.id,
            title = domain.title,
            body = domain.body,
            image = domain.image,
            category = domain.category
        )
    }


    fun mapFromEntityList(entities: List<BlogCacheEntity>): List<Blog> {
        return entities.map { it -> mapFromEntity(it) }
    }
}