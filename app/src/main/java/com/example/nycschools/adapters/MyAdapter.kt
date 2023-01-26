package com.example.nycschools.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nycschools.NewsActivity
import com.example.nycschools.R
import com.example.nycschools.models.Article
import com.example.nycschools.models.SchoolListItem
import kotlinx.android.synthetic.main.item_view.view.*

/**
 * an adapter class is a necessary class for us to bind our data to UI while displaying the
 * items in recyclerview. Here, we bind the data that is coming from api or database and
 * help t display to the view
 */

class MyAdapter(val context: Context, val schools: List<SchoolListItem>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
//class MyAdapter(private val context: Context, private val articles: List<Article>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    var onItemClick : ((Article) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val school = schools[position]
        holder.sourceName.text = school.school_name






//        val article = articles[position] //binding the data acc to the position
//        holder.sourceName.text = article.source.name
//        holder.sourceTitle.text = article.title
//        Glide.with(context).load(article.urlToImage).into(holder.image)
//        //we can add itemView onClick, if we want it to be clicked anywhere in the item
//        holder.itemView.setOnClickListener {  onItemClick?.invoke(article) }

        //below we are speicfying that we must click on the image to redirect to other screen
//        holder.image.setOnClickListener {
//            Toast.makeText(context, article.publishedAt, Toast.LENGTH_LONG).show()
//            onItemClick?.invoke(article)
//        }
    }

    override fun getItemCount(): Int {
//        return articles.size
        return schools.size
    }

    //storing the references of views
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sourceName = itemView.findViewById<TextView>(R.id.name_of_source)
        var sourceTitle = itemView.findViewById<TextView>(R.id.title)
        var image = itemView.findViewById<ImageView>(R.id.imageView)
    }

}