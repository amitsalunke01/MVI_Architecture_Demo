package com.amitsalunke.mvi_architecture_demo.retrofit

import com.amitsalunke.mvi_architecture_demo.model.Blog
import com.amitsalunke.mvi_architecture_demo.util.EntityMapper
import javax.inject.Inject

//class responsible to map network objects to domain objects
class NetworkMapper @Inject constructor() : EntityMapper<BlogNetworkEntity, Blog> {
    override fun mapFromEntity(entity: BlogNetworkEntity): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )
    }

    override fun mapToEntity(domain: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(
            id = domain.id,
            title = domain.title,
            body = domain.body,
            image = domain.image,
            category = domain.category
        )
    }

    fun mapFromEntityList(entities: List<BlogNetworkEntity>): List<Blog> {
        return entities.map { mapFromEntity(it) }
    }

}