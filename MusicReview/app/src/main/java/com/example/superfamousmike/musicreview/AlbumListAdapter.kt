package com.example.superfamousmike.musicreview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.rv_list_item_album.view.*

class AlbumListAdapter(private val context: Context) : RecyclerView.Adapter<AlbumListAdapter.AlbumListViewHolder>() {
    private var albums: List<Album>? = null// cached copy of Albums

    override fun getItemCount(): Int {
        return albums!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListViewHolder {
        return AlbumListViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_list_item_album, parent, false))
    }

    override fun onBindViewHolder(holder: AlbumListViewHolder, position: Int) {
        if (albums != null) {
            val currentAlbum: Album = albums!![position]
            holder.textViewAlbumName.text = currentAlbum.getTitle()
            holder.textViewArtistName.text = currentAlbum.getArtist()
        } else {
            holder.textViewAlbumName.text = "No data available"
        }
    }

    fun setAlbums(albums: List<Album>) {
        this.albums = albums
        notifyDataSetChanged()
    }

    class AlbumListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewAlbumName: TextView = view.textViewAlbumName
        val textViewArtistName: TextView = view.textViewArtistName
    }
}