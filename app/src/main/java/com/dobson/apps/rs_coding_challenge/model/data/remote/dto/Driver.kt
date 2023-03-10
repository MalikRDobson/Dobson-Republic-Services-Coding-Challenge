package com.dobson.apps.rs_coding_challenge.model.data.remote.dto

import com.dobson.apps.rs_coding_challenge.model.data.local.DriverDetailEntity

data class Driver(
    val id: String,
    val name: String
)

fun Driver.toDriverDetailEntity(): DriverDetailEntity = DriverDetailEntity(
    id = id,
    name = name
)
