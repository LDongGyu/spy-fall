package com.example.spyfall.database.dao

import androidx.room.*
import com.example.spyfall.database.Place

@Dao
interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlaces(vararg places: Place)

    @Update
    fun updatePlaces(vararg places: Place)

    @Delete
    fun deletePlaces(vararg places: Place)

    @Query("SELECT * FROM place")
    fun loadAllPlaces(): Array<Place>

    @Query("SELECT placeName FROM place WHERE placeId = :id")
    fun findPlaceWithID(id: Int): String
}