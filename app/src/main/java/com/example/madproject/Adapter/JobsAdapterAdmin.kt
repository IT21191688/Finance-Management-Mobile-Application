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
import com.example.madproject.Model.JobsModal
import com.example.madproject.R
import java.io.ByteArrayInputStream
class JobsAdapterAdmin(JobsModal:List<JobsModal>,internal var context: Context): RecyclerView.Adapter<JobsAdapterAdmin.JobsViewHolder>() {

    internal var joblist:List<JobsModal> = ArrayList()
    init{

        this.joblist=JobsModal;

    }
    inner class JobsViewHolder(view: View): RecyclerView.ViewHolder(view){
        var jobId: TextView =view.findViewById(R.id.jobId)
        var jobName: TextView =view.findViewById(R.id.jobName)
        var companyName: TextView =view.findViewById(R.id.companyName)
        var jobDiscripion: TextView =view.findViewById(R.id.jobDiscription)
        var siteLink: TextView =view.findViewById(R.id.siteLink)
        var image: ImageView =view.findViewById(R.id.imageView)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {

        val view= LayoutInflater.from(context).inflate(R.layout.jobadminread,parent,false)

        return JobsViewHolder(view)
    }
    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {

        val jobs=joblist[position]

        holder.jobId.text=jobs.id.toString()
        holder.jobName.text=jobs.jobName
        holder.companyName.text=jobs.companyName
        holder.jobDiscripion.text=jobs.jobDiscription;
        holder.siteLink.text=jobs.siteLink

        // Example base64-encoded byte code string
        val byteCodeString = jobs.image
// Decode the byte code string into a byte array
        val imageBytes = Base64.decode(byteCodeString, Base64.DEFAULT)
// Create a new bitmap object
        val imageBitmap = BitmapFactory.decodeStream(ByteArrayInputStream(imageBytes))
        //println(users.email);
       // println(users.image);
        holder.image.setImageBitmap(imageBitmap);
    }
    override fun getItemCount(): Int {
        println(joblist.size);
        println("heloooooooooooooooooo")
        return joblist.size;
    }
}

