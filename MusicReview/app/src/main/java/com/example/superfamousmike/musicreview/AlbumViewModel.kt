package com.example.superfamousmike.musicreview

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class AlbumViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var albumRepository: AlbumRepository
    private lateinit var allAlbums: LiveData<List<Album>>

    init {
        albumRepository = AlbumRepository(application)
        allAlbums = albumRepository.getAllAlbums()
    }

    fun getAllAlbums(): LiveData<List<Album>> {
        return this.allAlbums
    }

    fun insert(album: Album) {
        this.albumRepository.insert(album)
    }

}