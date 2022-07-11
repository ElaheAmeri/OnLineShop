package com.example.onlineshop.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinksXX(
    @Json(name = "collection")
    val collection: List<CollectionXX>,
    @Json(name = "self")
    val self: List<SelfXX>
)