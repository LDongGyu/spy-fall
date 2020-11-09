package com.ilsang.spyfall.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ilsang.spyfall.database.character.Character
import com.ilsang.spyfall.database.place.Place
import com.ilsang.spyfall.database.character.CharacterDao
import com.ilsang.spyfall.database.place.PlaceDao

@Database(entities = arrayOf(Place::class, Character::class), version = 2)
abstract class GameInfoDataBase: RoomDatabase(){
    abstract fun placeDao(): PlaceDao
    abstract fun characterDao(): CharacterDao

    companion object {
        private var INSTANCE: GameInfoDataBase? = null

        fun getInstance(context: Context): GameInfoDataBase? {
            if(INSTANCE == null){
                synchronized(GameInfoDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, GameInfoDataBase::class.java, "nowGame.db")
                        .createFromAsset("game.db")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}