package com.example.spyfall.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character (
    @PrimaryKey
    @ColumnInfo(name = "character_id") val characterId: Int,
    @ColumnInfo(name = "place_belong_id") val placeBelongId: Int,
    @ColumnInfo(name = "character_name") val characterName: String
)