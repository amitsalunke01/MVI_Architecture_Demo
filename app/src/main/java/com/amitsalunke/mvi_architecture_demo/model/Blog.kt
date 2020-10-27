package com.amitsalunke.mvi_architecture_demo.model

//blog model is the project level main data model for the blog listing
//in clean architecture it is also called as domain model it never changes
data class Blog(
    var id: Int,
    var title: String,
    var body: String,
    var image: String,
    var category: String
)