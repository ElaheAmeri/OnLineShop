package com.example.onlineshop.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinksX(
    @Json(name = "collection")
    val collection: List<CollectionX>,
    @Json(name = "self")
    val self: List<SelfX>,
//    @Json(name = "up")
//    val up: Up
)