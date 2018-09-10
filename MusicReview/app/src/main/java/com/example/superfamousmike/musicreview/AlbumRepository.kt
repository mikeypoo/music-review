package com.example.superfamousmike.musicreview

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask



class AlbumRepository(application: Application) {
    private lateinit var albumDao: AlbumDao
    private lateinit var allAlbums: LiveData<List<Album>>

    init {
        val db: AlbumRoomDatabase = AlbumRoomDatabase.getDatabase(application)
        albumDao = db.albumDao()
        allAlbums = albumDao.getAllAlbums()
    }

    fun getAllAlbums(): LiveData<List<Album>> {
        return this.allAlbums
    }

    fun insert(album: Album) {
        insertAsyncTask(albumDao).execute(album)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: AlbumDao) : AsyncTask<Album, Void, Void>() {
        override fun doInBackground(vararg params: Album): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}