package com.example.nycschools.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.R

/**
 * an adapter class is a necessary class for us to bind our data to UI while displaying the
 * items in recyclerview. Here, we bind the data that is coming from api or database and
 * help t display to the view
 */

class MyAdapter(val context: Context, val schools: String) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val school = schools[position]
        holder.schoolName.text = school.toString()
    }

    override fun getItemCount(): Int {
        return schools.length
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var schoolName = itemView.findViewById<TextView>(R.id.name_of_school)
    }
}