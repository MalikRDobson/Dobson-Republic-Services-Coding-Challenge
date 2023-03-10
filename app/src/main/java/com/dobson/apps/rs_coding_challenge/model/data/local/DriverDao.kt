package com.dobson.apps.rs_coding_challenge.model.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DriverDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDriverDetailList(driversDetail: List<DriverDetailEntity>)

    @Query("DELETE FROM driver_detail_table")
    suspend fun clearDriverDetailTable()

    @Query("SELECT * FROM driver_detail_table")
    suspend fun getAllDriversFromDb(): List< DriverDetailEntity>

}