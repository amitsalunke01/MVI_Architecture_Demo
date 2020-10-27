package com.amitsalunke.mvi_architecture_demo.util

//is responsible to convert data from blog - > BlogNetworkEntity and vice versa
//in our example case Blog class is domainModel and BlogNetworkEntity is Entity model
interface EntityMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel
    fun mapToEntity(domain: DomainModel): Entity
}