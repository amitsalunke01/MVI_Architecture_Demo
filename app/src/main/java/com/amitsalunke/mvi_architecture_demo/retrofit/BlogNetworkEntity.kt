package com.amitsalunke.mvi_architecture_demo.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
//This model is exclusive to retrofit
//once we get data from retrofit it needs to be converted to project level model i.e Blog
data class BlogNetworkEntity(
    @SerializedName("pk")
    @Expose
    var id: Int,

    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("body")
    @Expose
    var body: String,

    @SerializedName("image")
    @Expose
    var image: String,

    @SerializedName("category")
    @Expose
    var category: String

)