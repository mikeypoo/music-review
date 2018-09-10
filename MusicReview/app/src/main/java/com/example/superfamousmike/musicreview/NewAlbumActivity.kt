package com.example.superfamousmike.musicreview

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_new_album.*

class NewAlbumActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TITLE: String = "ALBUMS.TITLE"
        const val EXTRA_ARTIST: String = "ALBUMS.ARTIST"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_album)
        buttonSave.setOnClickListener {
            val replyIntent = Intent()
            if(TextUtils.isEmpty(editTextAlbumTitle.text) || TextUtils.isEmpty(editTextArtistName.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val newAlbumTitle: String = editTextAlbumTitle.text.toString()
                val newArtistName: String = editTextArtistName.text.toString()
                replyIntent.putExtra(EXTRA_TITLE, newAlbumTitle)
                replyIntent.putExtra(EXTRA_ARTIST, newArtistName)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}
