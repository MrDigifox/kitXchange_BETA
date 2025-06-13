package com.example.kitxchange

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ListingAdapter(
    private val ctx: Context,
    private var rows: List<Listing>
) : BaseAdapter() {

    /** Call this from BrowseActivity when the DB rows change */
    fun update(newRows: List<Listing>) {
        rows = newRows
        notifyDataSetChanged()
    }

    override fun getCount()  = rows.size
    override fun getItem(position: Int) = rows[position]
    override fun getItemId(position: Int) = rows[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val holder: VH
        val rowView: View

        if (convertView == null) {
            rowView = LayoutInflater.from(ctx).inflate(R.layout.row_listing, parent, false)
            holder = VH(
                rowView.findViewById(R.id.imgThumb),
                rowView.findViewById(R.id.tvTitle)
            )
            rowView.tag = holder
        } else {
            rowView = convertView
            holder = convertView.tag as VH
        }

        val item = rows[position]
        holder.title.text = "${item.title} — ${item.priceXmr} XMR"

        // Load thumbnail (or placeholder) with Glide
        val uri = item.imageUri?.let { Uri.parse(it) }
        Glide.with(ctx)
            .load(uri ?: android.R.drawable.ic_menu_gallery)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .centerCrop()
            .into(holder.thumb)

        return rowView
    }

    /** View-holder for better ListView performance */
    private data class VH(
        val thumb: ImageView,
        val title: TextView
    )
}
