package com.example.apivolleyjson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuoteAdapter(private val itemList: ArrayList<Quote>, mainActivity: MainActivity): RecyclerView.Adapter<QuoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mTitleTView.text = itemList[position].title
        holder.mAuthor.text = itemList[position].body

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mTitleTView: TextView = itemView.findViewById(R.id.quote_text)
        val mAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
    }
}


