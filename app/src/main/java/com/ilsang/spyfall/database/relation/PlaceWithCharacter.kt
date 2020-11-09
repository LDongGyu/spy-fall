package com.ilsang.spyfall.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.ilsang.spyfall.database.character.Character
import com.ilsang.spyfall.database.place.Place

data class PlaceWithCharacter(
    @Embedded val place: Place,
    @Relation(
        parentColumn = "placeId",
        entityColumn = "placeBelongId"
    )
    val characters: List<Character>
)