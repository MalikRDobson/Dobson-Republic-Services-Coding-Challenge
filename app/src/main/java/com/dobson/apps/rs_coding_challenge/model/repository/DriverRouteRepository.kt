package com.dobson.apps.rs_coding_challenge.model.repository

import com.dobson.apps.rs_coding_challenge.model.data.local.DriverDetailEntity
import com.dobson.apps.rs_coding_challenge.model.data.local.RouteDetailEntity
import com.dobson.apps.rs_coding_challenge.model.data.remote.dto.DriverRouteDto
import com.dobson.apps.rs_coding_challenge.util.Resource
import kotlinx.coroutines.flow.Flow

interface DriverRouteRepository {
    suspend fun getDriverRouteDetails(): Flow<Resource<DriverRouteDto>>

    suspend fun insertDriversDetails(drivers: List<DriverDetailEntity>)

    suspend fun insertRoutesDetails(routes: List<RouteDetailEntity>)

    suspend fun  getDriversDetails(): List<DriverDetailEntity>

    suspend fun deleteRoutes()

    suspend fun deleteDrivers()
    suspend fun getRoutesDetails(): List<RouteDetailEntity>
    suspend fun getRouteDetailById(id: Int): Resource<RouteDetailEntity>
    suspend fun getDriversDetails2(): Resource<List<DriverDetailEntity>>
}