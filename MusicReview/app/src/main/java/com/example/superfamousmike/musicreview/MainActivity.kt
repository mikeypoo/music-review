package com.example.superfamousmike.musicreview

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import android.app.Activity


class MainActivity : AppCompatActivity() {
    val NEW_ALBUM_ACTIVITY: Int = 1
    private lateinit var vm: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
    }

    private fun setUp() {
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewAlbumActivity::class.java)
            startActivityForResult(intent, NEW_ALBUM_ACTIVITY)
        }

        val adapter = AlbumListAdapter(this)

        vm = ViewModelProviders.of(this).get(AlbumViewModel::class.java)
        vm.getAllAlbums().observe(this, Observer<List<Album>> {
            adapter.setAlbums(it!!)
            recyclerViewAlbums.layoutManager = LinearLayoutManager(this)
            recyclerViewAlbums.adapter = adapter;
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_ALBUM_ACTIVITY && resultCode == Activity.RESULT_OK) {
            val album = Album(data!!.getStringExtra(NewAlbumActivity.EXTRA_TITLE),
                    data!!.getStringExtra(NewAlbumActivity.EXTRA_ARTIST))
            vm.insert(album)
        } else {
            Toast.makeText(
                    applicationContext,
                    "New album could not be saved",
                    Toast.LENGTH_LONG).show()
        }
    }

}
