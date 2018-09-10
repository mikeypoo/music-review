package com.example.superfamousmike.musicreview

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "album_table")
class Album(private val title: String, private val artist: String) {
    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0

    fun setId(id: Int) {
        this.id = id
    }

    fun getId(): Int {
        return this.id
    }

    fun getTitle(): String {
        return this.title;
    }

    fun getArtist(): String {
        return this.artist;
    }
}