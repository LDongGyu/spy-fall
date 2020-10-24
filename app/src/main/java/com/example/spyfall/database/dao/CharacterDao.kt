package com.example.spyfall.database.dao

import androidx.room.*

@Dao
interface CharacterDao{
    @Query("SELECT character_name FROM character WHERE place_belong_id = :id")
    fun findCharacterWithID(id: Int): Array<String>
}