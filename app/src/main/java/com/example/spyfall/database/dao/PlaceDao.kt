package com.example.spyfall.database.dao

import androidx.room.*
import com.example.spyfall.database.Place

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place")
    fun loadAllPlaces(): Array<Place>

    @Query("SELECT place_name FROM place WHERE place_id = :id")
    fun findPlaceWithID(id: Int): String
}
