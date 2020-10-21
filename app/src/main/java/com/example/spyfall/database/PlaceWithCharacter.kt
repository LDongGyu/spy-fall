package com.example.spyfall.database

import androidx.room.Embedded
import androidx.room.Relation

data class PlaceWithCharacter(
    @Embedded val place: Place,
    @Relation(
        parentColumn = "placeId",
        entityColumn = "placeBelongId"
    )
    val characters: List<Character>
)