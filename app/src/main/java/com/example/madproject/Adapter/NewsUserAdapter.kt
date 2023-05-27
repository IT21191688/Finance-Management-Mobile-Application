package com.example.madproject.Adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madproject.R
import java.io.ByteArrayInputStream

class NewsUserAdapter(NewsModal:List<NewsModal>, internal var context: Context): RecyclerView.Adapter<NewsUserAdapter.NewsViewHolder>() {
    internal var newsList:List<NewsModal> = ArrayList()
    init{
        this.newsList=NewsModal;
    }
    inner class NewsViewHolder(view: View): RecyclerView.ViewHolder(view){
        //var ArticleId: TextView =view.findViewById(R.id.articleId)
        var NewsTitle: TextView =view.findViewById(R.id.newsUserTitle)
        var NewsDate: TextView =view.findViewById(R.id.newsUserDate)
        var NewsDescripion: TextView =view.findViewById(R.id.newsUseDescription)
        var NewsSitelink: TextView =view.findViewById(R.id.newsUseSitelink)
        var Newsimage: ImageView =view.findViewById(R.id.readUseNewsImage)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.newsuserread,parent,false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val news=newsList[position]
        //holder.ArticleId.text=articles.article_Id.toString()
        holder.NewsTitle.text=news.news_Title
        holder.NewsDate.text=news.news_Date
        holder.NewsDescripion.text=news.news_description;
        holder.NewsSitelink.text=news.news_sitelink;
        // Example base64-encoded byte code string
        val byteCodeString = news.news_Image
// Decode the byte code string into a byte array
        val imageBytes = Base64.decode(byteCodeString, Base64.DEFAULT)
// Create a new bitmap object
        val imageBitmap = BitmapFactory.decodeStream(ByteArrayInputStream(imageBytes))
        holder.Newsimage.setImageBitmap(imageBitmap);
    }
    override fun getItemCount(): Int {
        println(newsList.size);
        println("heloooooooooooooooooo")
        return newsList.size;
    }
}