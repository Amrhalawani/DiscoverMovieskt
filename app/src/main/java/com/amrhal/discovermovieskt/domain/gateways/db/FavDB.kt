package com.amrhal.discovermovieskt.domain.gateways.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amrhal.discovermovieskt.domain.entities.FavMovie
import com.amrhal.discovermovieskt.domain.entities.Movie

@Database(entities = arrayOf(FavMovie::class), version = 1)
abstract class FavDB : RoomDatabase() {
    //must be abstract
    abstract fun favMoviesDAO(): FavMoviesDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FavDB? = null

        fun getDatabase(context: Context): FavDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavDB::class.java,
                    "fav_movies_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}