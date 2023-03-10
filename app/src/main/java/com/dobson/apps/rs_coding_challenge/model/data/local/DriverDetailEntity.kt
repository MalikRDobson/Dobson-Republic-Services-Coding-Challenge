package com.dobson.apps.rs_coding_challenge.model.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dobson.apps.rs_coding_challenge.model.data.remote.dto.Driver

@Entity(tableName = "driver_detail_table")
data class DriverDetailEntity(
    @PrimaryKey val id: String,
    val name: String
)

fun DriverDetailEntity.toDriversDto(): Driver = Driver( id = id, name = name)