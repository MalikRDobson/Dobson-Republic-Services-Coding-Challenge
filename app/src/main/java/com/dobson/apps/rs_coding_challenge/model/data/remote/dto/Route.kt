package com.dobson.apps.rs_coding_challenge.model.data.remote.dto

import com.dobson.apps.rs_coding_challenge.model.data.local.RouteDetailEntity

data class Route(
    val id: Int,
    val name: String,
    val type: String
)

fun Route.toRoutesDetailEntity(): RouteDetailEntity = RouteDetailEntity(
    id= id,
    name = name,
    type = type
)