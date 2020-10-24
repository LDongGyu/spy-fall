package com.example.spyfall.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.spyfall.database.character.Character
import com.example.spyfall.database.place.Place

data class PlaceWithCharacter(
    @Embedded val place: Place,
    @Relation(
        parentColumn = "placeId",
        entityColumn = "placeBelongId"
    )
    val characters: List<Character>
)