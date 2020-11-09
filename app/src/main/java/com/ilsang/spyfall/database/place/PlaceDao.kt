package com.ilsang.spyfall.database.place

import androidx.room.*

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place")
    fun loadAllPlaces(): Array<Place>

    @Query("SELECT place_name FROM place WHERE place_id = :id")
    fun findPlaceWithID(id: Int): String
}
