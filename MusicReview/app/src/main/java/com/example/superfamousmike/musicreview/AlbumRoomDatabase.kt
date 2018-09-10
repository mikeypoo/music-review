package com.example.superfamousmike.musicreview

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import android.support.annotation.NonNull

@Database(entities = [Album::class], version = 1)
abstract class AlbumRoomDatabase: RoomDatabase() {
    abstract fun albumDao(): AlbumDao

    companion object {
        private class PopulateDbAsync(db: AlbumRoomDatabase) : AsyncTask<Void, Void, Void>() {
            private val albumDao: AlbumDao = db.albumDao()

            override fun doInBackground(vararg params: Void?): Void? {
                albumDao.deleteAllAlbums()
                val album = Album("Title", "Artist Name")
                albumDao.insert(album)
                return null
            }

        }

        private val roomDbCallback = object:RoomDatabase.Callback() {
            override fun onOpen(@NonNull db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }

        private var INSTANCE: AlbumRoomDatabase? = null

        internal fun getDatabase(context: Context): AlbumRoomDatabase {
            if (INSTANCE == null) {
                synchronized (AlbumRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, AlbumRoomDatabase::class.java, "album_database").addCallback(roomDbCallback).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}