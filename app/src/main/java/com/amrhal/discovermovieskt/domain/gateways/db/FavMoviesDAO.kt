package com.amrhal.discovermovieskt.domain.gateways.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amrhal.discovermovieskt.domain.core.Constants.FAV_TABLE
import com.amrhal.discovermovieskt.domain.entities.FavMovie

@Dao
interface FavMoviesDAO {

    @Query("SELECT * from $FAV_TABLE")
    fun getFavMovies(): LiveData<List<FavMovie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun insert(movie: FavMovie)

    @Query("DELETE FROM $FAV_TABLE")
     fun deleteAll()
}