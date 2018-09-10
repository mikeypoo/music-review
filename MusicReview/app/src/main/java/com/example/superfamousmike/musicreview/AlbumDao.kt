package com.example.superfamousmike.musicreview

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface AlbumDao {
    @Insert
    fun insert(album: Album)

    @Query("DELETE FROM album_table")
    fun deleteAllAlbums()

    @Query("SELECT * FROM album_table ORDER BY artist ASC")
    fun getAllAlbums(): LiveData<List<Album>>
}